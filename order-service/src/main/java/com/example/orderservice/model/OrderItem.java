package com.example.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orderItems")
public class OrderItem {

    @Id
    private String id;
    private String orderId;
    private String productId;
    private String productName;
    private double price;
    private int quantity;
    private String vendorId;

}
