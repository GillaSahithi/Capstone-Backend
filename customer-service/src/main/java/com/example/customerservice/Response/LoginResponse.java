package com.example.customerservice.Response;

import com.example.customerservice.model.Customer;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class LoginResponse {
    private String token;
    private Customer customer;

    public LoginResponse(String token, Customer customer) {
        this.token = token;
        this.customer = customer;
    }
}
