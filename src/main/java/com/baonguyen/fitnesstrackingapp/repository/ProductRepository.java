package com.baonguyen.fitnesstrackingapp.repository;

import com.baonguyen.fitnesstrackingapp.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByCode(Integer code);
}
