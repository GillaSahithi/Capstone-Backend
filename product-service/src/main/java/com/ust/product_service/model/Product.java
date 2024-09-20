package com.ust.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
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
