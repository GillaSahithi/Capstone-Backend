package com.ust.product_service.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public record ProductDto(
        String id,

        @NotBlank(message = "Name cannot be Blank!")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,

        @NotBlank(message = "Description cannot be Blank!")
        @Size(min = 3, max = 50, message = "Description must be between 3 and 50 characters")
        String description,

        @NotNull(message = "Price cannot be null!")
        Double price,

        @NotNull(message = "Category ID cannot be null")
        @Size(min = 1, message = "Category ID must not be empty")
        String categoryId,

        @NotNull(message = "Vendor ID cannot be null")
        @Size(min = 1, message = "Vendor ID must not be empty")
        String vendorId,

        @Min(value = 0, message = "Stock quantity must be zero or positive")
        int stockQuantity,

        @Pattern(regexp = "^(http|https)://.*", message = "Image URL must be a valid URL")
        String imageUrl,

        List<@Size(min = 1) String> reviewIds
) {
}