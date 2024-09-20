package com.ust.product_service.dtoconverter;

import com.ust.product_service.dto.CategoryDto;
import com.ust.product_service.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDtoConverter {

    public static List<CategoryDto> toDTOs(List<Category> categories){
        return categories.stream().map(CategoryDtoConverter::convertToDto).toList();
    }

    public static CategoryDto convertToDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getProductIds()
        );
    }

    public static Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.id());
        category.setName(categoryDto.name());
        category.setDescription(categoryDto.description());
        category.setProductIds(categoryDto.productIds());
        return category;
    }
}
