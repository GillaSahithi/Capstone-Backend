package com.example.vendorservice.service;

import com.example.vendorservice.exception.VendorNotFoundException;
import com.example.vendorservice.feign.ProductServiceClient;
import com.example.vendorservice.model.Vendor;
import com.example.vendorservice.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    public Vendor createVendor(Vendor vendor) {
//        Vendor savedVendor = vendorRepository.save(vendor);
//        return savedVendor;
//    }

    public Vendor registerVendor(Vendor vendor) {
        vendor.setPassword(passwordEncoder.encode(vendor.getPassword()));
        return vendorRepository.save(vendor);
    }

    public Vendor login(String contactMail, String password) {
        Optional<Vendor> optionalVendor = vendorRepository.findByContactMail(contactMail);
        if (optionalVendor.isPresent()) {
            Vendor vendor = optionalVendor.get();
            if (passwordEncoder.matches(password, vendor.getPassword())) {
                return vendor;
            }
        }
        return null;
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Optional<Vendor> getVendorById(String id) {
        return vendorRepository.findById(id);
    }

    public Vendor updateVendor(String id, Vendor vendor) {
        Vendor existingVendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found with id: " + id));
        existingVendor.setName(vendor.getName());
        existingVendor.setDescription(vendor.getDescription());
        existingVendor.setContactMail(vendor.getContactMail());
        existingVendor.setPassword(vendor.getPassword());
        existingVendor.setContactPhone(vendor.getContactPhone());
        existingVendor.setAddress(vendor.getAddress());
        return vendorRepository.save(existingVendor);
    }

    public void deleteVendor(String id) {
        Vendor deletingVendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found with id: " + id));
        vendorRepository.delete(deletingVendor);
    }

    public Vendor getVendorByContactMail(String contactMail) {
        return vendorRepository.findByContactMail(contactMail)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found with contact mail: " + contactMail));
    }

    public String getVendorContactMailById(String id) {
        Vendor vendor = getVendorById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found with id: " + id));
        return vendor.getContactMail();
    }

    public void deleteVendorByContactMail(String contactMail) {
        Vendor vendor = vendorRepository.findByContactMail(contactMail)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found with contact mail: " + contactMail));
        vendorRepository.delete(vendor);
    }

    public List<?> getProductsByVendorId(String id) {
        return productServiceClient.getProductsByVendorId(id);
    }
}
