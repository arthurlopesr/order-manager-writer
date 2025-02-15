package com.ordermanagerwriter.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productId;
    private String name;
    private String description;
    private String category;
    private String price;
    private String ingredients;
    private String imageId;
}
