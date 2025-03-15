package com.ordermanagerwriter.application.domain.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record IngredientDTO(
        String ingredientId,
        String name
) {
}
