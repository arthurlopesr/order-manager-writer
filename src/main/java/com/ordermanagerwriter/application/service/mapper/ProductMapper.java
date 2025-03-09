package com.ordermanagerwriter.application.service.mapper;

import com.ordermanagerwriter.application.domain.dto.ProductDTO;
import com.ordermanagerwriter.application.domain.model.Product;
import com.ordermanagerwriter.infrastructure.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    Product toModel(ProductEntity product);

    @Mapping(target = "productId", ignore = true)
    ProductEntity modelToEntity(Product product);

    @Mapping(target = "productId", ignore = true)
    ProductEntity dtoToEntity(ProductDTO product);

    List<Product> toModels(List<ProductEntity> productEntities);

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
}
