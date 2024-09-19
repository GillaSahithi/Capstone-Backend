package com.example.vendorservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventDto(
        String id,

        @NotBlank(message = "Event name cannot be blank")
        String name,

        @NotNull(message = "Vendor ID cannot be null")
        String vendorId,

        @NotNull(message = "Event date cannot be null")
        LocalDateTime eventDate,

        @NotBlank(message = "Location cannot be blank")
        String location,

        @Size(max = 500, message = "Description must be less than or equal to 500 characters")
        String description


) {
}
