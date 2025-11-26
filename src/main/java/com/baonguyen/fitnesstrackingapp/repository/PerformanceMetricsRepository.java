package com.baonguyen.fitnesstrackingapp.repository;

import com.baonguyen.fitnesstrackingapp.entity.PerformanceMetrics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PerformanceMetricsRepository extends MongoRepository<PerformanceMetrics, String> {
}
