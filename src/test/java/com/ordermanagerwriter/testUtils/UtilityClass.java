package com.ordermanagerwriter.testUtils;


import com.ordermanagerwriter.application.model.Product;

import java.util.UUID;

public class UtilityClass {

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
}
