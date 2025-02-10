package com.ordermanagerwriter.application.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String productId;
    private String name;
    private String description;
    private String category;
    private String price;
    private String ingredients;
    private String imageId;
}
