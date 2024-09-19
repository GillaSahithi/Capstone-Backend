package com.example.vendorservice.repository;

import com.example.vendorservice.model.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorRepository extends MongoRepository<Vendor, String> {

    Optional<Vendor> findByContactMail(String contactMail);

//    List<?> findProductsByVendorId(String id);
}
