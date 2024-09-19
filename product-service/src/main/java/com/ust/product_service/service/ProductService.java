package com.ust.product_service.service;

import com.ust.product_service.exceptions.ProductNotFoundException;
import com.ust.product_service.model.Product;
import com.ust.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
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

    public Product updateProduct(String id, Product partialUpdate) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    if (partialUpdate.getName() != null) {
                        existingProduct.setName(partialUpdate.getName());
                    }
                    if (partialUpdate.getPrice() != null) {
                        existingProduct.setPrice(partialUpdate.getPrice());
                    }
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
         productRepository.deleteById(id);
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