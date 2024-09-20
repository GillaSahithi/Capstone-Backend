package com.ust.product_service.repository;

import com.ust.product_service.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface CategoryRepository extends MongoRepository<Category, String> {
      // Add Additional Methods if required in the future.
}
