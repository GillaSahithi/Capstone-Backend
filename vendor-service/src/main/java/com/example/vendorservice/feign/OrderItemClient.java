package com.example.vendorservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "order-service", url = "http://localhost:8003/api/order-items")
public interface OrderItemClient {

    @GetMapping("/vendor/{vendorId}")
    List<?> getOrderItemsByVendorId(@PathVariable("vendorId") String vendorId);
}
