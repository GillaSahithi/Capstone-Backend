package com.ust.product_service.service;

import com.ust.product_service.exceptions.CategoryNotFoundException;
import com.ust.product_service.model.Category;
import com.ust.product_service.model.Product;
import com.ust.product_service.repository.CategoryRepository;
import com.ust.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public Category createCategory(Category category){
        log.debug("service posting");
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(String id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            return optionalCategory;
        }else{
            throw new CategoryNotFoundException("Category Not Found");
        }
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Optional<Category> updateCategory(String id, Category category){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            return Optional.of(categoryRepository.save(existingCategory));
        }else{
            throw new CategoryNotFoundException("Category Not Found");
        }
    }

//    public void deleteCategory(String id){
//        categoryRepository.deleteById(id);
//    }
public void deleteCategory(String id) {
    if (categoryRepository.findById(id).isPresent()) {
        categoryRepository.deleteById(id);
    }
}


    public List<Product> getProductsByCategoryId(String categoryId){
        return productRepository.findByCategoryId(categoryId);
    }
}