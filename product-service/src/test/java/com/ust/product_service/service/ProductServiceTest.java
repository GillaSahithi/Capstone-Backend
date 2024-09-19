//package com.ust.product_service.service;
//
//import com.ust.product_service.model.Product;
//import com.ust.product_service.repository.ProductRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class ProductServiceTest {
//
//    @Mock
//    private ProductRepository productRepository;
//
//    private Product productWithNullName;
//    private Product productWithNullPrice;
//    private Product productWithNullNameAndPrice;
//    private Product productWithName;
//    private Product productWithPrice;
//    private Product productWithNameAndPrice;
//
//    @InjectMocks
//    private ProductService productService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        productWithNullName = new Product(1L, null, "A description", 100.0, "Category");
//        productWithNullPrice = new Product(1L, "Product ABC", "A description", null, "Category");
//        productWithNullNameAndPrice = new Product(1L, null, "A description", null, "Category");
//        productWithName = new Product(1L, "Product ABC", "A description", 100.0, "Category");
//        productWithPrice = new Product(1L, "Product ABC", "A description", 200.0, "Category");
//        productWithNameAndPrice = new Product(1L, "Product ABC", "A description", 100.0, "Category");
//    }
//
//    @Test
//    @DisplayName("Test for creating product with null name")
//    public void testCreateProductWithNullName() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            productService.createProduct(productWithNullName);
//        });
//    }
//
//    @Test
//    @DisplayName("Test for creating product with null price")
//    public void testCreateProductWithNullPrice() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            productService.createProduct(productWithNullPrice);
//        });
//    }
//
//    @Test
//    @DisplayName("Test for creating product with null name and price")
//    public void testCreateProductWithNullNameAndPrice() {
//         IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            productService.createProduct(productWithNullNameAndPrice);
//        });
//        assertEquals("Product name cannot be null or empty", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Test for creating product with name and price")
//    public void testCreateProductWithNameAndPrice() {
//        when(productRepository.save(productWithNameAndPrice)).thenReturn(productWithNameAndPrice);
//        Product createdProduct = productService.createProduct(productWithNameAndPrice);
//        assertEquals(productWithNameAndPrice, createdProduct);
//        verify(productRepository, times(1)).save(productWithNameAndPrice);
//    }
//
//    @Test
//    @DisplayName("Test for creating product with only name")
//    public void testCreateProductWithName() {
//        when(productRepository.save(productWithName)).thenReturn(productWithName);
//        Product createdProduct = productService.createProduct(productWithName);
//        assertEquals(productWithName, createdProduct);
//        verify(productRepository, times(1)).save(productWithName);
//    }
//
//    @Test
//    @DisplayName("Test for creating product with only price")
//    public void testCreateProductWithPrice() {
//        when(productRepository.save(productWithPrice)).thenReturn(productWithPrice);
//        Product createdProduct = productService.createProduct(productWithPrice);
//        assertEquals(productWithPrice, createdProduct);
//        verify(productRepository, times(1)).save(productWithPrice);
//    }
//}