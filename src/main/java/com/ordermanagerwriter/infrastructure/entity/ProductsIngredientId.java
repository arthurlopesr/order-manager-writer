package com.ordermanagerwriter.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductsIngredientId {

    @Column(name = "product_id")
    private String productId;

    @Column(name = "ingredient_id")
    private String ingredientId;
}
