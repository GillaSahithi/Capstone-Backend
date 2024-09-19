package com.example.orderservice.repository;

import com.example.orderservice.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {

    Payment findByRazorpayOrderId(String razorpayOrderId);
    List<Payment> findPaymentsByCustomerId(String customerId);
    List<Payment> findPaymentsByVendorIds(String vendorId);
}
