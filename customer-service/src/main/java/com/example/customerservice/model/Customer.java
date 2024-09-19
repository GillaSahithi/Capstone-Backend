package com.example.customerservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@RequiredArgsConstructor
public class Customer {
    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;

}
