package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.domain.dto.IngredientDTO;
import com.ordermanagerwriter.application.domain.dto.ProductDTO;
import com.ordermanagerwriter.application.exception.BusinessException;
import com.ordermanagerwriter.application.exception.CategoryNotFoundException;
import com.ordermanagerwriter.application.exception.ProductNotFoundException;
import com.ordermanagerwriter.application.domain.model.Product;
import com.ordermanagerwriter.application.service.ProductService;
import com.ordermanagerwriter.application.service.mapper.ProductMapper;
import com.ordermanagerwriter.infrastructure.entity.CategoryEntity;
import com.ordermanagerwriter.infrastructure.entity.IngredientEntity;
import com.ordermanagerwriter.infrastructure.repository.CategoryRepository;
import com.ordermanagerwriter.infrastructure.repository.IngredientRepository;
import com.ordermanagerwriter.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public void createProduct(ProductDTO product) {
        try {
            var categoryEntity = findCategoryById(product.categoryId());
            var ingredientEntities = mapIngredientIdsToEntities(product.ingredients().stream()
                    .map(IngredientDTO::ingredientId)
                    .collect(Collectors.toList()));

            var productEntity = ProductMapper.INSTANCE.dtoToEntity(product);
            productEntity.setCategory(categoryEntity);
            productEntity.setIngredients(ingredientEntities);

            productRepository.save(productEntity);
        } catch (Exception e) {
            throw new BusinessException(String.format("Product creation failed: %s", e.getMessage()).toString());
        }
    }

    @Override
    public Product findProductById(String productId) {
        var productEntity = productRepository.findById(productId)
                .orElseThrow(() -> ProductNotFoundException.create(productId));
        return ProductMapper.INSTANCE.toModel(productEntity);
    }

    @Override
    public List<Product> findAllProducts() {
        var productEntities = productRepository.findAll();
        return ProductMapper.INSTANCE.toModels(productEntities);
    }

    @Override
    public void deleteProductById(String productId) {
        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            throw ProductNotFoundException.create(productId);
        }
    }

    private CategoryEntity findCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    private IngredientEntity findIngredientById(String ingredientId) {
        return ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new BusinessException(String.format("Ingredient with id %s not found", ingredientId)));
    }

    private List<IngredientEntity> mapIngredientIdsToEntities(List<String> ingredientIds) {
        return ingredientIds.stream()
                .map(this::findIngredientById)
                .collect(Collectors.toList());
    }
}
