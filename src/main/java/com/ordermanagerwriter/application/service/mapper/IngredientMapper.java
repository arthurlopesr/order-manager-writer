package com.ordermanagerwriter.application.service.mapper;

import com.ordermanagerwriter.application.domain.model.Ingredient;
import com.ordermanagerwriter.infrastructure.entity.IngredientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    Ingredient toModel(IngredientEntity ingredientEntity);
}
