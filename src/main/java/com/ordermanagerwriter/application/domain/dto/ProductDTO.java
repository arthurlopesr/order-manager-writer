package com.ordermanagerwriter.application.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record ProductDTO(
        String productId,
        String name,
        String description,
        String categoryId,
        String price,
        List<IngredientDTO> ingredients,
        String imageId
) {
}
