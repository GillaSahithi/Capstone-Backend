package com.example.customerservice.service;

import com.example.customerservice.exception.CustomerNotFoundException;
import com.example.customerservice.feign.OrderClient;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrderClient orderClient;

    public List<Map<String, Object>> getOrdersByCustomerId(String customerId) {
        return orderClient.getOrdersByCustomerId(customerId);
    }

    public Customer registerCustomer(Customer customer) {
        // Encrypt the password
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    public Customer loginCustomer(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            return customer;
        }
        throw new CustomerNotFoundException("Invalid email or password");
    }

//    public Customer createCustomer(Customer customer) {
//        Customer savedCustomer = customerRepository.save(customer);
//        return savedCustomer;
//    }

    public Customer getCustomerById(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found id: "+id));
        return customer;
    }

    public Customer updateCustomer(String id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found id: "+id));
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPassword(customer.getPassword());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setAddress(customer.getAddress());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(String id) {
        var deletingCustomer = customerRepository.findById(id)
                        .orElseThrow(()->new CustomerNotFoundException("Customer not found id: "+id));
        customerRepository.delete(deletingCustomer);
    }

    public Customer getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        return customer;
    }

    public String getCustomerEmailById(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found id: "+id));
        return customer.getEmail();
    }
}
