package com.ust.product_service.service;

import com.ust.product_service.dto.Vendor;
import com.ust.product_service.exceptions.ProductNotFoundException;
import com.ust.product_service.feign.VendorClient;
import com.ust.product_service.model.Category;
import com.ust.product_service.model.Product;
import com.ust.product_service.repository.CategoryRepository;
import com.ust.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private  VendorClient vendorClient;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product createProduct(Product product) {
        Vendor vendor = vendorClient.getVendorById(product.getVendorId());
        if (vendor == null) {
            throw new IllegalArgumentException("Vendor with ID " + product.getVendorId() + " does not exist");
        }

        String vendorName = (String) vendor.getName();
        product.setVendorName(vendorName);

        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than 0");
        }
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    public Product updateProduct(String id, Product productDetails) {
        Product product = getProductById(id);

        // Fetch vendor details if vendorId is updated
        if (!product.getVendorId().equals(productDetails.getVendorId())) {
            Vendor vendor = vendorClient.getVendorById(productDetails.getVendorId());
            String vendorName = (String) vendor.getName();
            product.setVendorName(vendorName);
        }

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setImageUrl(productDetails.getImageUrl());
        product.setCategoryId(productDetails.getCategoryId());

        Product updatedProduct = productRepository.save(product);
        updateCategoryWithProduct(updatedProduct.getCategoryId(), updatedProduct.getId());
        return updatedProduct;
    }

    private void updateCategoryWithProduct(String categoryId, String productId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (category.getProductIds() == null) {
            category.setProductIds(new ArrayList<>());
        }

        if (!category.getProductIds().contains(productId)) {
            category.getProductIds().add(productId);
            categoryRepository.save(category);
        }
    }

    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
         productRepository.delete(product);
    }

    public List<Product> getProductsByCategoryId(String id) {
        return productRepository.findByCategoryId(id);
    }

    public List<Product> getProductsByVendorId(String id) {
        return productRepository.findByVendorId(id);
    }

    public Product updateProductStock(String id,int quantity) {
        Product product=getProductById(id);
        if (product.getStockQuantity()<quantity){
            throw new IllegalArgumentException("Not enough stock");
        }

        product.setStockQuantity(product.getStockQuantity()-quantity);
        return productRepository.save(product);
    }
}