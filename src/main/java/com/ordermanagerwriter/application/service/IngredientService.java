package com.ordermanagerwriter.application.service;

import com.ordermanagerwriter.application.domain.dto.IngredientDTO;
import com.ordermanagerwriter.application.domain.model.Ingredient;

public interface IngredientService {
    Ingredient createIngredient(IngredientDTO ingredientDTO);
}
