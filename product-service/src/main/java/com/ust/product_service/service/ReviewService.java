package com.ust.product_service.service;

import com.ust.product_service.exceptions.ReviewNotFoundException;
import com.ust.product_service.model.Product;
import com.ust.product_service.model.Review;
import com.ust.product_service.repository.ProductRepository;
import com.ust.product_service.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public Review createReview(Review review) {
        Review savedReview = reviewRepository.save(review);
        Product product = productRepository.findById(review.getProductId())
                .orElseThrow(() -> new ReviewNotFoundException("Product not found with id: " + review.getProductId()));
        product.getReviewIds().add(savedReview.getId());
        return reviewRepository.save(review);
    }

    public Review getReviewById(String id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id));
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review updateReview(String id, Review review) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id));
        existingReview.setComment(review.getComment());
        existingReview.setRating(review.getRating());
        return reviewRepository.save(existingReview);
    }

    public void deleteReview(String id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id));
        reviewRepository.deleteById(id);
    }

    public List<Review> getReviewsByProductId(String productId) {
        return reviewRepository.findByProductId(productId);
    }
    public List<Review> getReviewsByCustomerId(String customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }
}
