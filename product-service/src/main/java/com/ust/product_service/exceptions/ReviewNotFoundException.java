package com.ust.product_service.exceptions;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String s) {
        super(s);
    }
}
