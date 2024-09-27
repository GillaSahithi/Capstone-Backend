package com.ust.product_service.controller;

import com.ust.product_service.dto.ReviewDto;
import com.ust.product_service.dtoconverter.ReviewDtoConverter;
import com.ust.product_service.model.Review;
import com.ust.product_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewDtoConverter reviewDtoConverter;

    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto reviewDto){
        Review review = reviewDtoConverter.toEntity(reviewDto);
        Review createdReview = reviewService.createReview(review);
        return reviewDtoConverter.toDto(createdReview);
    }

    @GetMapping
    public List<ReviewDto> getAllReviews(){
        List<Review> reviews = reviewService.getAllReviews();
        return reviewDtoConverter.toDTOs(reviews);
    }

    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable String id){
        Review review = reviewService.getReviewById(id);
        return reviewDtoConverter.toDto(review);
    }

    @PutMapping("/{id}")
    public ReviewDto updateReview(@PathVariable String id, @RequestBody ReviewDto reviewDto){
        Review review = reviewDtoConverter.toEntity(reviewDto);
        Review updatedReview = reviewService.updateReview(id, review);
        return reviewDtoConverter.toDto(updatedReview);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable String id){
        reviewService.deleteReview(id);
    }

    @GetMapping("/product/{productId}")
    public List<ReviewDto> getReviewsByProductId(@PathVariable String productId){
        List<Review> reviews = reviewService.getReviewsByProductId(productId);
        return reviewDtoConverter.toDTOs(reviews);
    }

    @GetMapping("/user/{userId}")
    public List<ReviewDto> getReviewsByCustomerId(@PathVariable String customerId){
        List<Review> reviews = reviewService.getReviewsByCustomerId(customerId);
        return reviewDtoConverter.toDTOs(reviews);
    }
}
