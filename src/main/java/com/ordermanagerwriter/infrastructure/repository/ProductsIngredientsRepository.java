package com.ordermanagerwriter.infrastructure.repository;

import com.ordermanagerwriter.infrastructure.entity.ProductsIngredientId;
import com.ordermanagerwriter.infrastructure.entity.ProductsIngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsIngredientsRepository extends JpaRepository<ProductsIngredientsEntity, ProductsIngredientId> {
    List<ProductsIngredientsEntity> findByProductsIngredientIdProductId(String productId);
}
