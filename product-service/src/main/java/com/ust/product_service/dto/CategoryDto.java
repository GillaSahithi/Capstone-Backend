package com.ust.product_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public record CategoryDto(

        String id,

        @NotEmpty(message = "Name cannot be empty!")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,

        @NotEmpty(message = "Description cannot be empty!")
        @Size(min = 3, max = 50, message = "Description must be between 3 and 50 characters")
        String description,

        List<String> productIds
) {
}
