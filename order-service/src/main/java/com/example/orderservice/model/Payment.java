package com.example.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;
    private String orderId;
    private String razorpayOrderId;
    private String PaymentId;
    private double amount;
    private String Currency;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String customerId;
    private List<String> vendorIds;

}
