package com.ordermanagerwriter.application.domain.dto;

import lombok.Builder;

@Builder
public record CategoryDTO(
        String categoryId,
        String name,
        String description,
        String emoji
) {
}
