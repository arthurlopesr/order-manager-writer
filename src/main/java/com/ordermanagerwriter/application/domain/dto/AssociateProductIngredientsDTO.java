package com.ordermanagerwriter.application.domain.dto;

import lombok.Builder;

@Builder
public record AssociateProductIngredientsDTO(
        String ingredientId
) {
}
