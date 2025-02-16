package com.ordermanagerwriter.application.exception;

public class ProductNotFoundException extends BaseException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException create(String productId) {
        String message = String.format("Product with ID %s not found", productId);
        return new ProductNotFoundException(message);
    }
}
