package com.ordermanagerwriter.application.service.mapper;

import com.ordermanagerwriter.application.domain.model.Category;
import com.ordermanagerwriter.infrastructure.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    Category toModel(CategoryEntity category);

    CategoryEntity toEntity(Category product);

    List<Category> toModelList(List<CategoryEntity> categoryEntities);

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
}
