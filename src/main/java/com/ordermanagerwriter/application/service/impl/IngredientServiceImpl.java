package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.domain.dto.IngredientDTO;
import com.ordermanagerwriter.application.domain.model.Ingredient;
import com.ordermanagerwriter.application.service.IngredientService;
import com.ordermanagerwriter.application.service.mapper.IngredientsMapper;
import com.ordermanagerwriter.infrastructure.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient createIngredient(IngredientDTO ingredientDTO) {
        var ingredientEntity = IngredientsMapper.INSTANCE.dtoToEntity(ingredientDTO);
        var savedIngredient = ingredientRepository.save(ingredientEntity);
        return IngredientsMapper.INSTANCE.entityToModel(savedIngredient);
    }
}
