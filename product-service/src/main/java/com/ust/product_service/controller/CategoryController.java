package com.ust.product_service.controller;

import com.ust.product_service.dto.CategoryDto;
import com.ust.product_service.dto.ProductDto;
import com.ust.product_service.dtoconverter.CategoryDtoConverter;
import com.ust.product_service.dtoconverter.ProductDtoConverter;
import com.ust.product_service.model.Category;
import com.ust.product_service.model.Product;
import com.ust.product_service.service.CategoryService;
import com.ust.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/categories")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductDtoConverter productDtoConverter;

    @PostMapping
    public CategoryDto  createCategory(@RequestBody CategoryDto categoryDto) {
        Category  category = CategoryDtoConverter.toEntity(categoryDto);
        Category createdCategory = categoryService.createCategory(category);
        log.debug("controller posting");
        return CategoryDtoConverter.convertToDto(createdCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id) {
        Optional<Category> optionalCategory = categoryService.getCategoryById(id);
        if (optionalCategory.isPresent()) {
            CategoryDto categoryDto = CategoryDtoConverter.convertToDto(optionalCategory.get());
            return ResponseEntity.ok(categoryDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return CategoryDtoConverter.toDTOs(categories);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String id, @RequestBody CategoryDto categoryDto) {
        Category category = CategoryDtoConverter.toEntity(categoryDto);
        Optional<Category> updatedCategory = categoryService.updateCategory(id, category);
        CategoryDto updatedCategoryDto = CategoryDtoConverter.convertToDto(updatedCategory.get());
        return ResponseEntity.ok(updatedCategoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category Deleted!");
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable String id) {
        List<Product> products = productService.getProductsByCategoryId(id);
        List<ProductDto> productDto = ProductDtoConverter.toDTOs(products);
        return ResponseEntity.ok(productDto);
    }
}
