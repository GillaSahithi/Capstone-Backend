package com.ust.product_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
public class Vendor {
    @Id
    private String id;
    private String name;
    private String description;
    private String contactMail;
    private String contactPhone;
    private String address;
    private String password;
}
