package com.ust.product_service.controller;


import com.ust.product_service.dto.ProductDto;
import com.ust.product_service.dtoconverter.ProductDtoConverter;
import com.ust.product_service.model.Product;
import com.ust.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductDtoConverter dtoConverter;

    @PostMapping
    public ProductDto  createProduct(@RequestBody ProductDto productDto) {
        Product product = ProductDtoConverter.toEntity(productDto);
        Product createdProduct = productService.createProduct(product);
        return ProductDtoConverter.convertToDto(createdProduct);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ProductDtoConverter.toDTOs(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable String name) {
       List<Product> products = productService.getProductsByName(name);
       if(products.isEmpty()){
           return ResponseEntity.notFound().build();
       }
       Product product = products.getFirst();
       ProductDto productDto = ProductDtoConverter.convertToDto(product);
       return ResponseEntity.ok(productDto);
    }

    @PutMapping("/update/{id}")
    public ProductDto updateProduct(@PathVariable String id,@RequestBody ProductDto productDto) {
        Product product = ProductDtoConverter.toEntity(productDto);
        Product updatedProduct = productService.updateProduct(id, product);
        return ProductDtoConverter.convertToDto(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product Deleted!");
    }

    @GetMapping("/category/{id}")
    public List<ProductDto> getProductsByCategory(@PathVariable String id) {
        List<Product> products = productService.getProductsByCategoryId(id);
        return ProductDtoConverter.toDTOs(products);
    }

    @GetMapping("/vendor/{id}")
    public List<ProductDto> getProductsByVendorId(@PathVariable String id) {
        List<Product> products = productService.getProductsByVendorId(id);
        return ProductDtoConverter.toDTOs(products);
    }

    @PutMapping("/{id}/update-stock")
    public ProductDto updateProductStock(@PathVariable String id, @RequestParam int quantity) {
        Product product = productService.updateProductStock(id, quantity);
        return ProductDtoConverter.convertToDto(product);
    }
}