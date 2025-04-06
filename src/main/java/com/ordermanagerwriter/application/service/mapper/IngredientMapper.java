package com.ordermanagerwriter.application.service.mapper;

import com.ordermanagerwriter.application.domain.dto.IngredientDTO;
import com.ordermanagerwriter.application.domain.model.Ingredient;
import com.ordermanagerwriter.infrastructure.entity.IngredientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {
    IngredientEntity dtoToEntity(IngredientDTO dto);

    IngredientDTO entityToDto(IngredientEntity entity);

    Ingredient toModel(IngredientEntity ingredientEntity);

    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);
}
