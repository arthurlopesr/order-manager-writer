package com.ordermanagerwriter.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productId;
    private String name;
    private String description;
    private Category category;
    private String price;
    private List<Ingredient> ingredients;
    private String imageId;
}
