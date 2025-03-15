package com.ordermanagerwriter.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products_ingredients")
public class ProductsIngredientsEntity {

    @EmbeddedId
    private ProductsIngredientId productsIngredientId;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private IngredientEntity ingredient;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "product_name")
    private String productName;
}
