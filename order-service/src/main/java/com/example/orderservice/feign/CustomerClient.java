package com.example.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8001/api/customer")
public interface CustomerClient {

    @GetMapping("/{email}/email")
    String getCustomerEmailById(@PathVariable String email);

}