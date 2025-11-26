package com.baonguyen.fitnesstrackingapp.repository;

import com.baonguyen.fitnesstrackingapp.entity.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WorkoutRepository extends MongoRepository<Workout, String> {

    Optional<Workout> findByworkoutDate(LocalDate date);
}
