package com.ordermanagerwriter.application.service.mapper;

import com.ordermanagerwriter.application.model.Category;
import com.ordermanagerwriter.infrastructure.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryEntity toModel(CategoryEntity category);

    CategoryEntity toEntity(Category product);

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
}
