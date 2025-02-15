package com.ordermanagerwriter.testUtils;


import com.ordermanagerwriter.application.model.Category;
import com.ordermanagerwriter.application.model.Product;
import com.ordermanagerwriter.application.service.mapper.ProductMapper;
import com.ordermanagerwriter.infrastructure.entity.ProductEntity;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class TestUtilityClass {

    public static Product createTestProduct() {
        return Product.builder()
                .productId(UUID.randomUUID().toString())
                .name("Test Product")
                .description("This is a test product")
                .price("100,00")
                .category("Test Category")
                .imageId("test-image-id")
                .ingredients("Test Ingredients")
                .build();
    }

    public static ProductEntity createTestProductEntity(Product product) {
        return ProductMapper.INSTANCE.toEntity(product);
    }

    public static Category createTestCategory() {
        return Category.builder()
                .categoryId(UUID.randomUUID().toString())
                .name("Test Category")
                .description("This is a test category")
                .emoji("🍔")
                .build();
    }
}
