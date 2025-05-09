package com.ordermanagerwriter.testUtils;


import com.ordermanagerwriter.application.domain.dto.CategoryDTO;
import com.ordermanagerwriter.application.domain.dto.IngredientDTO;
import com.ordermanagerwriter.application.domain.dto.ProductDTO;
import com.ordermanagerwriter.application.domain.model.Category;
import com.ordermanagerwriter.application.domain.model.Ingredient;
import com.ordermanagerwriter.application.domain.model.Product;
import com.ordermanagerwriter.application.domain.model.ProductIngredients;
import com.ordermanagerwriter.application.service.mapper.ProductMapper;
import com.ordermanagerwriter.infrastructure.entity.*;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@UtilityClass
public class TestUtilityClass {

    public static ProductDTO createTestProduct() {
        return ProductDTO.builder()
                .productId(UUID.randomUUID().toString())
                .name("Test Product")
                .description("This is a test product")
                .price("100,00")
                .categoryId("e829e6b4-32f7-41b8-aeb6-b408df551121")
                .imageId("test-image-id")
                .ingredients(createTestIngredientDTO(createTestIngredients()))
                .build();
    }

    public static ProductEntity createTestProductEntity(ProductDTO product) {
        return ProductMapper.INSTANCE.dtoToEntity(product);
    }

    public static Product createTestProductModel() {
        return Product.builder()
                .productId(UUID.randomUUID().toString())
                .name("Test Product")
                .description("This is a test product")
                .price("100,00")
                .category(createTestCategory())
                .imageId("test-image-id")
                .ingredients(createTestIngredients())
                .build();
    }

    public static Category createTestCategory() {
        return Category.builder()
                .categoryId("test-category-id")
                .name("Test Category")
                .description("This is a test category")
                .emoji("🍔")
                .build();
    }

    public static CategoryEntity createTestCategoryEntity(Category category) {
        return CategoryEntity.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .emoji(category.getEmoji())
                .build();
    }

    public static List<Ingredient> createTestIngredients() {
        return List.of(
                Ingredient.builder()
                        .ingredientId("test-ingredient-id-1")
                        .name("Test Ingredient 1")
                        .build(),
                Ingredient.builder()
                        .ingredientId("test-ingredient-id-2")
                        .name("Test Ingredient 2")
                        .build());
    }

    public static List<IngredientDTO> createTestIngredientDTO(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient -> IngredientDTO.builder()
                        .ingredientId(ingredient.getIngredientId())
                        .name(ingredient.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public static IngredientEntity createTestIngredientEntity(Ingredient ingredient) {
        return IngredientEntity.builder()
                .ingredientId(ingredient.getIngredientId())
                .name(ingredient.getName())
                .build();
    }

    public static List<ProductsIngredientsEntity> createTestProductsIngredientsEntity(ProductEntity productEntity, List<IngredientEntity> ingredientEntities) {
        return ingredientEntities.stream()
                .map(ingredientEntity -> {
                    var productsIngredientId = ProductsIngredientId.builder()
                            .productId(productEntity.getProductId())
                            .ingredientId(ingredientEntity.getIngredientId())
                            .build();

                    return ProductsIngredientsEntity.builder()
                            .productsIngredientId(productsIngredientId)
                            .ingredient(ingredientEntity)
                            .product(productEntity)
                            .productName(productEntity.getName())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public static ProductsIngredientsEntity createProductsIngredientsEntity(ProductEntity productEntity, IngredientEntity ingredientEntity) {
        var productsIngredientId = ProductsIngredientId.builder()
                .productId(productEntity.getProductId())
                .ingredientId(ingredientEntity.getIngredientId())
                .build();

        return ProductsIngredientsEntity.builder()
                .productsIngredientId(productsIngredientId)
                .ingredient(ingredientEntity)
                .product(productEntity)
                .productName(productEntity.getName())
                .build();
    }

    public static List<IngredientEntity> createTestIngredientEntities(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient -> IngredientEntity.builder()
                        .ingredientId(ingredient.getIngredientId())
                        .name(ingredient.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public static ProductIngredients createTestProductIngredients() {
        return ProductIngredients.builder()
                .product(createTestProductModel())
                .ingredient(createTestIngredients())
                .build();
    }

    public static Ingredient createTestIngredient() {
        return Ingredient.builder()
                .ingredientId("test-ingredient-id")
                .name("Test Ingredient")
                .build();
    }

    public static IngredientDTO createTestIngredientDTO() {
        return IngredientDTO.builder()
                .ingredientId("test-ingredient-id")
                .name("Test Ingredient")
                .build();
    }

    public static CategoryDTO createTestCategoryDTO() {
        return CategoryDTO.builder()
                .categoryId("test-category-id")
                .name("Test Category")
                .description("This is a test category")
                .emoji("🍔")
                .build();
    }

    public ProductEntity createProductEntity(ProductDTO product) {
        return ProductMapper.INSTANCE.dtoToEntity(product);
    }
}
