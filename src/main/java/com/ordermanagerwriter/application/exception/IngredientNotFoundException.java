package com.ordermanagerwriter.application.exception;

public class IngredientNotFoundException extends BaseException {
    public IngredientNotFoundException(String message) {
        super(message);
    }

    public static IngredientNotFoundException create(String categoryId) {
        String message = String.format("Ingredient with ID %s not found", categoryId);
        return new IngredientNotFoundException(message);
    }
}
