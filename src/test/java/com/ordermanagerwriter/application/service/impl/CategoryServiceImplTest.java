package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.exception.BusinessException;
import com.ordermanagerwriter.application.model.Category;
import com.ordermanagerwriter.infrastructure.entity.CategoryEntity;
import com.ordermanagerwriter.infrastructure.entity.ProductEntity;
import com.ordermanagerwriter.infrastructure.repository.CategoryRepository;
import com.ordermanagerwriter.testUtils.TestUtilityClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @BeforeEach
    void setUp() {
        category = TestUtilityClass.createTestCategory();
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

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            categoryService.createCategory(category);
        });

        assertEquals("Category creation failed: %s", exception.getMessage());
    }
}