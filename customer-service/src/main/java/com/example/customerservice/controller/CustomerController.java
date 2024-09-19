package com.example.customerservice.controller;

import com.example.customerservice.converter.CustomerDtoConverter;
import com.example.customerservice.dto.CustomerDto;
import com.example.customerservice.model.Customer;
import com.example.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerDtoConverter converter;

    @PostMapping
    public ResponseEntity<CustomerDto> saveUser(@Valid @RequestBody CustomerDto dto) {
        Customer customer = converter.toEntity(dto);
        customer = customerService.createCustomer(customer);
        var responseBody = converter.toDto(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable String id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(converter.toDto(customer));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id, @Valid @RequestBody CustomerDto dto) {
        Customer updatedCustomer = customerService.updateCustomer(id, converter.toEntity(dto));
        return ResponseEntity.ok(converter.toDto(updatedCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable String email) {
        var customer = customerService.getCustomerByEmail(email);
        if (customer != null) {
            return ResponseEntity.ok(converter.toDto(customer));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/email")
    public ResponseEntity<String> getCustomerEmailById(@PathVariable String id){
        String email = customerService.getCustomerEmailById(id);
        if(email != null){
            return ResponseEntity.ok(email);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<Map<String, Object>>> getOrdersByCustomerId(@PathVariable String customerId) {
        List<Map<String, Object>> orders = customerService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

}
