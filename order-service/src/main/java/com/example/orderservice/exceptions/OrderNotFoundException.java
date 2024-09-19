package com.example.orderservice.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String orderNotFound) {
        super(orderNotFound);
    }
}
