package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.domain.dto.AssociateProductIngredientsDTO;
import com.ordermanagerwriter.application.domain.dto.ProductDTO;
import com.ordermanagerwriter.application.domain.model.Product;
import com.ordermanagerwriter.application.exception.BusinessException;
import com.ordermanagerwriter.application.exception.CategoryNotFoundException;
import com.ordermanagerwriter.application.exception.IngredientNotFoundException;
import com.ordermanagerwriter.application.exception.ProductNotFoundException;
import com.ordermanagerwriter.application.service.ProductService;
import com.ordermanagerwriter.application.service.mapper.IngredientMapper;
import com.ordermanagerwriter.application.service.mapper.ProductMapper;
import com.ordermanagerwriter.infrastructure.entity.CategoryEntity;
import com.ordermanagerwriter.infrastructure.entity.ProductsIngredientId;
import com.ordermanagerwriter.infrastructure.entity.ProductsIngredientsEntity;
import com.ordermanagerwriter.infrastructure.repository.CategoryRepository;
import com.ordermanagerwriter.infrastructure.repository.IngredientRepository;
import com.ordermanagerwriter.infrastructure.repository.ProductRepository;
import com.ordermanagerwriter.infrastructure.repository.ProductsIngredientsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final ProductsIngredientsRepository productsIngredientsRepository;

    @Override
    public void createProduct(ProductDTO product) {
        try {
            var categoryEntity = findCategoryById(product.categoryId());
            var productEntity = ProductMapper.INSTANCE.dtoToEntity(product);
            productEntity.setCategory(categoryEntity);
            var savedProduct = productRepository.save(productEntity);

            product.ingredients().forEach(ingredientDTO -> {
                var associateDTO = AssociateProductIngredientsDTO.builder()
                        .ingredientId(ingredientDTO.ingredientId())
                        .build();
                associateIngredients(savedProduct.getProductId(), associateDTO);
            });
        } catch (Exception e) {
            throw new BusinessException(String.format("Product creation failed: %s", e.getMessage()));
        }
    }

    @Override
    public Product findProductById(String productId) {
        var productIngredients = productsIngredientsRepository.findByProductsIngredientIdProductId(productId);
        var ingredientModels = productIngredients.stream()
                .map(ProductsIngredientsEntity::getIngredient)
                .map(IngredientMapper.INSTANCE::toModel)
                .toList();

        var productEntity = productRepository.findById(productId)
                .orElseThrow(() -> ProductNotFoundException.create(productId));

        var productModel = ProductMapper.INSTANCE.toModel(productEntity);
        productModel.setIngredients(ingredientModels);
        return productModel;
    }

    @Override
    public List<Product> findAllProducts() {
        var productEntities = productRepository.findAll();
        var productIngredients = productEntities.stream()
                .map(productEntity -> productsIngredientsRepository.findByProductsIngredientIdProductId(productEntity.getProductId()))
                .flatMap(List::stream)
                .toList();
        var ingredientModels = productIngredients.stream()
                .map(ProductsIngredientsEntity::getIngredient)
                .map(IngredientMapper.INSTANCE::toModel)
                .toList();

        var productModels = ProductMapper.INSTANCE.toModels(productEntities);
        productModels.forEach(product -> product.setIngredients(ingredientModels));
        return productModels;
    }

    @Override
    public void deleteProductById(String productId) {
        try {
            var productIngredients = productsIngredientsRepository.findByProductsIngredientIdProductId(productId);
            productsIngredientsRepository.deleteAll(productIngredients);
            productRepository.deleteById(productId);
        } catch (Exception e) {
            throw ProductNotFoundException.create(productId);
        }
    }

    private CategoryEntity findCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> CategoryNotFoundException.create(categoryId));
    }

    private void associateIngredients(String productId, AssociateProductIngredientsDTO associateProductIngredientsDTO) {
        var ingredientEntity = ingredientRepository.findById(associateProductIngredientsDTO.ingredientId())
                .orElseThrow(() -> IngredientNotFoundException.create(associateProductIngredientsDTO.ingredientId()));
        var productEntity = productRepository.findById(productId)
                .orElseThrow(() -> ProductNotFoundException.create(productId));

        var productsIngredientId = ProductsIngredientId.builder()
                .productId(productEntity.getProductId())
                .ingredientId(ingredientEntity.getIngredientId())
                .build();

        var productsIngredientsEntity = ProductsIngredientsEntity.builder()
                .productsIngredientId(productsIngredientId)
                .ingredient(ingredientEntity)
                .product(productEntity)
                .productName(productEntity.getName())
                .build();

        productsIngredientsRepository.save(productsIngredientsEntity);
    }
}
