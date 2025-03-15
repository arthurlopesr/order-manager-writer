package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.domain.dto.ProductDTO;
import com.ordermanagerwriter.application.domain.model.ProductIngredients;
import com.ordermanagerwriter.application.exception.BusinessException;
import com.ordermanagerwriter.application.exception.ProductNotFoundException;
import com.ordermanagerwriter.application.service.mapper.IngredientMapper;
import com.ordermanagerwriter.application.service.mapper.ProductMapper;
import com.ordermanagerwriter.infrastructure.entity.*;
import com.ordermanagerwriter.infrastructure.repository.CategoryRepository;
import com.ordermanagerwriter.infrastructure.repository.IngredientRepository;
import com.ordermanagerwriter.infrastructure.repository.ProductRepository;
import com.ordermanagerwriter.infrastructure.repository.ProductsIngredientsRepository;
import com.ordermanagerwriter.testUtils.TestUtilityClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.ordermanagerwriter.testUtils.TestUtilityClass.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private ProductsIngredientsRepository productsIngredientsRepository;

    private ProductDTO product;
    private CategoryEntity categoryEntity;
    private IngredientEntity ingredientEntity;
    private ProductEntity productEntity;
    private ProductsIngredientsEntity productsIngredientsEntity;
    private ProductIngredients productIngredients;

    @BeforeEach
    void setUp() {
        product = TestUtilityClass.createTestProduct();
        categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(product.categoryId());
        ingredientEntity = createTestIngredientEntity(createTestIngredient());
        productEntity = createProductEntity(product);
        productsIngredientsEntity = createProductsIngredientsEntity(productEntity, ingredientEntity);
        productIngredients = createTestProductIngredients();
    }

    @Test
    @DisplayName("Should create a product successfully")
    void createProduct() {
        when(categoryRepository.findById(product.categoryId())).thenReturn(Optional.of(categoryEntity));
        productEntity.setCategory(categoryEntity);
        when(productRepository.save(productEntity)).thenReturn(productEntity);
        when(ingredientRepository.findById("test-ingredient-id-1")).thenReturn(Optional.of(ingredientEntity));
        when(ingredientRepository.findById("test-ingredient-id-2")).thenReturn(Optional.of(ingredientEntity));
        when(productRepository.findById(productEntity.getProductId())).thenReturn(Optional.of(productEntity));
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
        productService.createProduct(product);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    @DisplayName("Should throw CategoryNotFoundException when category is not found")
    void createProductThrowsCategoryNotFoundException() {
        when(categoryRepository.findById(product.categoryId())).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> productService.createProduct(product));
        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    @DisplayName("Should throw BusinessException when product creation fails")
    void createProductThrowsBusinessException() {
        when(categoryRepository.findById(product.categoryId())).thenReturn(Optional.of(categoryEntity));
        doThrow(new RuntimeException("Database error")).when(productRepository).save(any(ProductEntity.class));
        BusinessException exception = assertThrows(BusinessException.class, () -> productService.createProduct(product));
        assertEquals("Product creation failed: Database error", exception.getMessage());
    }

    @Test
    @DisplayName("Should return a product successfully")
    void findProductById() {
        when(productsIngredientsRepository.findByProductsIngredientIdProductId(product.productId())).thenReturn(List.of(productsIngredientsEntity));
        var ingredientModels = Stream.of(productsIngredientsEntity)
                .map(ProductsIngredientsEntity::getIngredient)
                .map(IngredientMapper.INSTANCE::toModel)
                .toList();
        when(productRepository.findById(product.productId())).thenReturn(Optional.of(productEntity));
        var productModel = ProductMapper.INSTANCE.toModel(productEntity);
        productModel.setIngredients(ingredientModels);
        var result = productService.findProductById(product.productId());
        assertEquals(productModel, result);
    }

    @Test
    @DisplayName("Should throw ProductNotFoundException when product is not found")
    void findProductByIdThrowsProductNotFoundException() {
        when(productRepository.findById(product.productId())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.findProductById(product.productId()));
    }

    @Test
    @DisplayName("Should return all products successfully")
    void findAllProducts() {
        var productEntities = List.of(productEntity);
        when(productRepository.findAll()).thenReturn(productEntities);
        when(productsIngredientsRepository.findByProductsIngredientIdProductId(productEntity.getProductId())).thenReturn(List.of(productsIngredientsEntity));
        var ingredientModels = List.of(IngredientMapper.INSTANCE.toModel(ingredientEntity));
        var productModels = List.of(ProductMapper.INSTANCE.toModel(productEntity));
        productModels.forEach(product -> product.setIngredients(ingredientModels));
        var result = productService.findAllProducts();
        assertEquals(productModels, result);
    }

    @Test
    @DisplayName("Should delete a product successfully")
    void deleteProductById() {
        when(productsIngredientsRepository.findByProductsIngredientIdProductId(product.productId())).thenReturn(List.of(productsIngredientsEntity));
        productService.deleteProductById(product.productId());
        verify(productsIngredientsRepository, times(1)).deleteAll(List.of(productsIngredientsEntity));
        verify(productRepository, times(1)).deleteById(product.productId());
    }
}