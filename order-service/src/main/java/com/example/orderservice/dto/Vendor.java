package com.example.orderservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Data
@NoArgsConstructor
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
