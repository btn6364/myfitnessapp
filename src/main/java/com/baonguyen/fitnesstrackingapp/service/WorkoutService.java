package com.baonguyen.fitnesstrackingapp.service;

import com.baonguyen.fitnesstrackingapp.dto.WorkoutResponse;
import com.baonguyen.fitnesstrackingapp.entity.PerformanceMetrics;
import com.baonguyen.fitnesstrackingapp.entity.User;
import com.baonguyen.fitnesstrackingapp.entity.Workout;
import com.baonguyen.fitnesstrackingapp.repository.PerformanceMetricsRepository;
import com.baonguyen.fitnesstrackingapp.repository.UserRepository;
import com.baonguyen.fitnesstrackingapp.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor

@Service
public class WorkoutService {

    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;
    private final PerformanceMetricsRepository performanceMetricsRepository;

    /**
     * Add a new workout for the authenticated user
     * @param authentication the authentication object
     * @param workout the workout details
     * @return response entity indicating the result
     */
    public ResponseEntity<?> addWorkout(Authentication authentication, WorkoutResponse workout) {
        var user = (User) authentication.getPrincipal();
        workoutRepository.findByworkoutDate(workout.workoutDate()).
                ifPresent(e -> {
                    throw new IllegalStateException("Workout already exists");
                });
        var performanceMetrics = workout.performanceMetricsResponse();
        var newPerformanceMetrics = PerformanceMetrics.builder()
                .caloriesBurned(performanceMetrics.caloriesBurned())
                .duration(performanceMetrics.duration())
                .intensity(performanceMetrics.intensity())
                .build();
        performanceMetricsRepository.save(newPerformanceMetrics);
        var newWorkout = Workout.builder()
                .workoutDate(workout.workoutDate())
                .exerciseType(workout.exerciseType())
                .build();
        newWorkout.setPerformanceMetrics(newPerformanceMetrics);
        workoutRepository.save(newWorkout);
        user.getWorkouts().add(newWorkout);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Delete a workout by its ID
     * @param id the workout ID
     * @return response entity indicating the result
     */
    public ResponseEntity<?> deleteWorkout(String id) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var workout = workoutRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Workout does not exist"));
        workoutRepository.delete(workout);
        user.getWorkouts().remove(workout);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
