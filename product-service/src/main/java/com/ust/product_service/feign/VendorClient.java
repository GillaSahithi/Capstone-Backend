package com.ust.product_service.feign;

import com.ust.product_service.dto.Vendor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "VendorService", url = "http://localhost:8000/api/vendor")
public interface VendorClient {

    @GetMapping("/{id}")
    Vendor getVendorById(@PathVariable("id") String id);
}
