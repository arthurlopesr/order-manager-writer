package com.ordermanagerwriter.application.service.mapper;

import com.ordermanagerwriter.application.model.Product;
import com.ordermanagerwriter.infrastructure.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    Product toModel(ProductEntity product);

    @Mapping(target = "productId", ignore = true)
    ProductEntity toEntity(Product product);

    List<Product> toModels(List<ProductEntity> productEntities);

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
}
