package com.example.vendorservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record VendorDto (
        String id,

        @NotBlank(message = "Name cannot be blank")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @Size(max = 255, message = "Description cannot exceed 255 characters")
        String description,

        @NotBlank(message = "Contact email cannot be blank")
        @Email(message = "Invalid email format")
        String contactMail,

        @NotBlank(message = "Contact phone cannot be blank")
        @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
        String contactPhone,

        @NotBlank(message = "Address cannot be blank")
        @Size(max = 100, message = "Address name cannot exceed 50 characters")
        String address,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
        String password
){
}
