package com.ust.product_service.dtoconverter;

import com.ust.product_service.dto.ProductDto;
import com.ust.product_service.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDtoConverter {
    public static List<ProductDto> toDTOs(List<Product> products){
        return products.stream().map(ProductDtoConverter::convertToDto).toList();
    }

    public static ProductDto convertToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategoryId(),
                product.getVendorId(),
                product.getStockQuantity(),
                product.getImageUrl(),
                product.getReviewIds()


        );
    }

    public static Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.id());
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        product.setCategoryId(productDto.categoryId());
        product.setVendorId(productDto.vendorId());
        product.setStockQuantity(productDto.stockQuantity());
        product.setImageUrl(productDto.imageUrl());
        product.setReviewIds(productDto.reviewIds());
        return product;
    }
}
