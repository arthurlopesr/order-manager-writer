package com.ordermanagerwriter.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class ProductEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private String price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<IngredientEntity> ingredients;

    @Column(name = "image_id")
    private String imageId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

}
