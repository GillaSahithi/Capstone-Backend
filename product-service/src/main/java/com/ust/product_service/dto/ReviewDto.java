package com.ust.product_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ReviewDto(

        @NotBlank(message = "Id cannot be Blank!")
        @Size(min = 3, max = 50, message = "Id must be between 3 and 50 characters")
        String id,

        @NotBlank(message = "Product Id cannot be Blank!")
        String productId,

        @NotBlank(message = "User Id cannot be Blank!")
        String customerId,

        @NotBlank(message = "Rating cannot be Blank!")
        @Size(min = 1, max = 5, message = "Rating must be between 1 and 5")
        int rating,

        @NotBlank(message = "Comment cannot be Blank!")
        @Size(min = 3, max = 500, message = "Comment must be between 3 and 500 characters")
        String comment

) {
}
