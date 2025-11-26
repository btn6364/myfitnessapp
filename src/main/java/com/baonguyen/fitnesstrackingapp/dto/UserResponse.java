package com.baonguyen.fitnesstrackingapp.dto;

import java.util.List;

public record UserResponse(
        String firstname,
        String lastname,
        String email,

        List<WorkoutResponse> workouts,
        List<ProductResponse> products
) {
}
