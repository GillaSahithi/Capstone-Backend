package com.ust.product_service.repository;

import com.ust.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByVendorId(String vendorId);

    List<Product> findByCategoryId(String categoryId);

    List<Product> findByName(String name);
}
