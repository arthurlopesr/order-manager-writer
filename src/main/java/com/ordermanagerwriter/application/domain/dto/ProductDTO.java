package com.ordermanagerwriter.application.domain.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record ProductDTO(
        String productId,
        String name,
        String description,
        String categoryId,
        String price,
        String ingredients,
        String imageId
) {
}
