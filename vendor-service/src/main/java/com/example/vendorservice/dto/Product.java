package com.example.vendorservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
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
