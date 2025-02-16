package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.exception.CategoryNotFoundException;
import com.ordermanagerwriter.application.model.Category;
import com.ordermanagerwriter.infrastructure.entity.CategoryEntity;
import com.ordermanagerwriter.infrastructure.repository.CategoryRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    Category category;

    CategoryEntity categoryEntity;

    @BeforeEach
    void setUp() {
        category = TestUtilityClass.createTestCategory();
        categoryEntity = TestUtilityClass.createTestCategoryEntity(category);
    }

    @Test
    @DisplayName("Should create a category")
    void createCategory() {
        categoryService.createCategory(category);
        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }

    @Test
    @DisplayName("Should throw BusinessException when category creation fails")
    void createCategoryThrowsException() {
        doThrow(new RuntimeException("Database error")).when(categoryRepository).save(any());

        CategoryNotFoundException exception = assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.createCategory(category);
        });

        assertEquals("Category with ID test-category-id not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should find a category by id")
    void findCategoryById() {
        when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.of(categoryEntity));

        Category foundCategory = categoryService.findCategoryById(category.getCategoryId());

        assertEquals(category, foundCategory);
        verify(categoryRepository, times(1)).findById(category.getCategoryId());
    }

    @Test
    @DisplayName("Should throw BusinessException when category not found")
    void findCategoryByIdThrowsException() {
        when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.empty());
        CategoryNotFoundException exception = assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.findCategoryById(category.getCategoryId());
        });

        assertEquals("Category with ID test-category-id not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should find all categories")
    void findAllCategories() {
        when(categoryRepository.findAll()).thenReturn(List.of(categoryEntity));

        List<Category> categories = categoryService.findAllCategories();

        assertEquals(List.of(category), categories);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no categories found")
    void findAllCategories_withEmptyList() {
        when(categoryRepository.findAll()).thenReturn(List.of());

        List<Category> categories = categoryService.findAllCategories();

        assertEquals(List.of(), categories);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should delete a category by id")
    void deleteCategoryById() {
        categoryService.deleteCategoryById(category.getCategoryId());

        verify(categoryRepository, times(1)).deleteById(category.getCategoryId());
    }

    @Test
    @DisplayName("Should throw BusinessException when category not found in deleteCategoryById")
    void deleteCategoryByIdThrowsException() {
        doThrow(new RuntimeException("Database error")).when(categoryRepository).deleteById(any());
        CategoryNotFoundException exception = assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.deleteCategoryById(category.getCategoryId());
        });

        assertEquals("Category with ID test-category-id not found", exception.getMessage());
    }
}