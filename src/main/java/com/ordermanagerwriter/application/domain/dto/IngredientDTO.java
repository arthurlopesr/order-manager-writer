package com.ordermanagerwriter.application.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public record IngredientDTO(
        String ingredientId,
        String name,
        ProductDTO products
) {
}
