package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.exception.BusinessException;
import com.ordermanagerwriter.application.model.Product;
import com.ordermanagerwriter.infrastructure.entity.CategoryEntity;
import com.ordermanagerwriter.infrastructure.entity.ProductEntity;
import com.ordermanagerwriter.infrastructure.repository.CategoryRepository;
import com.ordermanagerwriter.infrastructure.repository.ProductRepository;
import com.ordermanagerwriter.testUtils.TestUtilityClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    private Product product;
    private CategoryEntity categoryEntity;

    @BeforeEach
    void setUp() {
        product = TestUtilityClass.createTestProduct();
        categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(product.getCategoryId());
    }

    @Test
    @DisplayName("Should create a product successfully")
    void createProduct() {
        when(categoryRepository.findById(product.getCategoryId())).thenReturn(Optional.of(categoryEntity));
        productService.createProduct(product);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    @DisplayName("Should throw CategoryNotFoundException when category is not found")
    void createProductThrowsCategoryNotFoundException() {
        when(categoryRepository.findById(product.getCategoryId())).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> productService.createProduct(product));
        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    @DisplayName("Should throw BusinessException when product creation fails")
    void createProductThrowsBusinessException() {
        when(categoryRepository.findById(product.getCategoryId())).thenReturn(Optional.of(categoryEntity));
        doThrow(new RuntimeException("Database error")).when(productRepository).save(any(ProductEntity.class));
        BusinessException exception = assertThrows(BusinessException.class, () -> productService.createProduct(product));
        assertEquals("Product creation failed: Database error", exception.getMessage());
    }
}