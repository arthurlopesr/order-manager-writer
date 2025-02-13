package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.model.Product;
import com.ordermanagerwriter.infrastructure.entity.ProductEntity;
import com.ordermanagerwriter.infrastructure.repository.ProductRepository;
import com.ordermanagerwriter.testUtils.UtilityClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
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
        product = UtilityClass.createTestProduct();
    }

    @Test
    @DisplayName("Should create a product")
    void createProduct() {
        productService.createProduct(product);
    }
}