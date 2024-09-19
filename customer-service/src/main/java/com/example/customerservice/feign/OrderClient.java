package com.example.customerservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "order-service", url = "http://localhost:8003/api/orders")
public interface OrderClient {

    @GetMapping("/customer/{customerId}")
    List<Map<String, Object>> getOrdersByCustomerId(@PathVariable("customerId") String customerId);
}
