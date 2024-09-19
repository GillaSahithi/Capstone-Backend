package com.example.orderservice.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Customer {

       @Id
        private String id;

        private String name;
        private String email;
        private String password;
        private String phoneNumber;
        private String address;

}