package com.example.orderservice.feign;

import com.example.orderservice.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.CacheRequest;
import java.util.Map;

@FeignClient(name = "product-service", url = "http://localhost:8002/api/products")
public interface ProductClient {

    @GetMapping("/{id}")
    Map<String, Object> getProductById(@PathVariable("id") String id);

    @PutMapping("/{id}/update-stock")
    void updateProductStock(@PathVariable("id") String id, @RequestParam("quantity") int quantity);
}
