package com.ust.product_service.repository;

import com.ust.product_service.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review,String> {

    List<Review> findByProductId(String productId);
    List<Review> findByCustomerId(String customerId);

}
