package com.ordermanagerwriter.infrastructure.repository;

import com.ordermanagerwriter.infrastructure.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<IngredientEntity, String> {
}
