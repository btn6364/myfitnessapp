package com.baonguyen.fitnesstrackingapp.dto;

public record PerformanceMetricsResponse(
        Double duration,
        Integer caloriesBurned,
        Integer intensity
) {
}
