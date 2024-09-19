package com.ust.product_service.dtoconverter;

import com.ust.product_service.dto.ReviewDto;
import com.ust.product_service.model.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewDtoConverter {

    public static List<ReviewDto> toDTOs(List<Review> reviews){
        return reviews.stream().map(ReviewDtoConverter::toDto).toList();
    }
    public static ReviewDto toDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getProductId(),
                review.getCustomerId(),
                review.getRating(),
                review.getComment()
        );
    }

    public Review toEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.id());
        review.setProductId(reviewDto.productId());
        review.setCustomerId(reviewDto.customerId());
        review.setRating(reviewDto.rating());
        review.setComment(reviewDto.comment());
        return review;
    }
}
