package com.ordermanagerwriter.application.domain.dto;

import com.ordermanagerwriter.application.domain.model.Ingredient;
import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record ProductDTO(
        String productId,
        String name,
        String description,
        String categoryId,
        String price,
        List<Ingredient> ingredients,
        String imageId
) {
}
