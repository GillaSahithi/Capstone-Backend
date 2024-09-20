package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private String categoryId;
    private String vendorId;
    private String vendorName;
    private int stockQuantity;
    private String imageUrl;
    private List<String> reviewIds=new ArrayList<>();
}
