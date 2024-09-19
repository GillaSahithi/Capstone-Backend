package com.ust.product_service.service;

import com.ust.product_service.model.Category;
import com.ust.product_service.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {
    @Mock
    private CategoryRepository  categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setName("Electronics");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category createdCategory = categoryService.createCategory(category);

        assertEquals("Electronics", createdCategory.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category();
        category.setId("1");
        category.setName("Electronics");

        when(categoryRepository.findById("1")).thenReturn(java.util.Optional.of(category));

        Optional<Category> retrievedCategory = categoryService.getCategoryById("1");

        assertEquals("Electronics", retrievedCategory.get().getName());
        verify(categoryRepository, times(1)).findById("1");
    }

    @Test
    public void testGetCategoryByIdNotFound() {
        when(categoryRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> categoryService.getCategoryById("1"));
        verify(categoryRepository, times(1)).findById("1");
    }

    @Test
    public void testUpdateProduct() {
        Category category = new Category();
        category.setId("1");
        category.setName("Electronics");

        when(categoryRepository.findById("1")).thenReturn(java.util.Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Optional<Category> updatedCategory = categoryService.updateCategory("1", category);

        assertEquals("Electronics", updatedCategory.get().getName());
        verify(categoryRepository, times(1)).findById("1");
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    public void testUpdateProductNotFound() {
        Category category = new Category();
        category.setId("1");
        category.setName("Electronics");
        when(categoryRepository.findById("1")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> categoryService.updateCategory("1", category));
        verify(categoryRepository, times(1)).findById("1");
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testDeleteCategory() {
        Category category = new Category();
        category.setId("1");
        category.setName("Electronics");
        when(categoryRepository.findById("1")).thenReturn(java.util.Optional.of(category));
        categoryService.deleteCategory("1");
        verify(categoryRepository, times(1)).findById("1");
        verify(categoryRepository, times(1)).deleteById("1");
    }

    @Test
    public void testGetAllCategories() {
        categoryService.getAllCategories();
        verify(categoryRepository, times(1)).findAll();
    }

}