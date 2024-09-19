package com.example.orderservice.feign;

import com.example.orderservice.dto.Vendor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "vendor-service",url = "http://localhost:8000/api/vendors")
public interface VendorClient {

    @GetMapping("/id/{id}")
    String getVendorContactMailById(@PathVariable("id") String vendorId);

    @GetMapping("/{id}")
    Map<String,Object> getVendorById(@PathVariable("id") String vendorId);
}
