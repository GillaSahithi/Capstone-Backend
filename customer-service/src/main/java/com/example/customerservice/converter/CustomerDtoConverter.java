package com.example.customerservice.converter;

import com.example.customerservice.dto.CustomerDto;
import com.example.customerservice.model.Customer;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {
    public CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getPhoneNumber(),
                customer.getAddress()
        );
    }

    public Customer toEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.id());
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setPassword(customerDto.password());
        customer.setPhoneNumber(customerDto.phoneNumber());
        customer.setAddress(customerDto.address());
        return customer;
    }
}
