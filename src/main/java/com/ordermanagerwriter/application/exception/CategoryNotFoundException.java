package com.ordermanagerwriter.application.exception;

public class CategoryNotFoundException extends BaseException {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public static CategoryNotFoundException create(String categoryId) {
        String message = String.format("Category with ID %s not found", categoryId);
        return new CategoryNotFoundException(message);
    }
}
