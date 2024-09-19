package com.example.vendorservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@RequiredArgsConstructor
@Document(collection = "vendors")
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
