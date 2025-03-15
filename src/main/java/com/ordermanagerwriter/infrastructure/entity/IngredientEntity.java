package com.ordermanagerwriter.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredients")
public class IngredientEntity {

    @Id
    @Column(name = "ingredient_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ingredientId;

    @Column(name = "name", nullable = false)
    private String name;
}
