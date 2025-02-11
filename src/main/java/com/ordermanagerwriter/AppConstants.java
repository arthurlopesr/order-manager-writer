package com.ordermanagerwriter;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {

    @UtilityClass
    public static class ErrorMessages {
        public static final String PRODUCT_NOT_FOUND = "Product not found";
        public static final String PRODUCT_ALREADY_EXISTS = "Product already exists";
        public static final String PRODUCT_CREATION_FAILED = "Failed to create product";
        public static final String GENERIC_ERROR = "An error occurred while processing the request";
    }
}
