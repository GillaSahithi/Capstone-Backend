package com.example.vendorservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:8002/api/products")
public interface ProductServiceClient {

    @GetMapping("/vendor/{id}")
    List<?> getProductsByVendorId(@PathVariable("id")  String id);
}