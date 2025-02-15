package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.exception.BusinessException;
import com.ordermanagerwriter.application.model.Product;
import com.ordermanagerwriter.infrastructure.entity.ProductEntity;
import com.ordermanagerwriter.infrastructure.repository.ProductRepository;
import com.ordermanagerwriter.testUtils.TestUtilityClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp() {
        product = TestUtilityClass.createTestProduct();
    }

    @Test
    @DisplayName("Should create a product")
    void createProduct() {
        productService.createProduct(product);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    @DisplayName("Should throw BusinessException when product creation fails")
    void createCategoryThrowsException() {
        doThrow(new RuntimeException("Database error")).when(productRepository).save(any());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productService.createProduct(product);
        });

        assertEquals("Product creation failed: %s", exception.getMessage());
    }
}