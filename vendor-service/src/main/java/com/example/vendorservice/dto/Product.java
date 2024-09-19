package com.example.vendorservice.dto;


import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data

public class Product {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String categoryId;
    private String vendorId;
    private int stockQuantity;
    private String imageUrl;
    private List<String> reviewIds=new ArrayList<>();
}
