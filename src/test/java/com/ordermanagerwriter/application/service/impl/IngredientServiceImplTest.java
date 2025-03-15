package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.domain.dto.IngredientDTO;
import com.ordermanagerwriter.application.domain.model.Ingredient;
import com.ordermanagerwriter.infrastructure.entity.IngredientEntity;
import com.ordermanagerwriter.infrastructure.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.ordermanagerwriter.testUtils.TestUtilityClass.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @InjectMocks
    IngredientServiceImpl ingredientService;

    @Mock
    IngredientRepository ingredientRepository;

    private IngredientDTO ingredientDTO;
    private IngredientEntity ingredientEntity;
    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        ingredient = createTestIngredient();
        ingredientDTO = createTestIngredientDTO();
        ingredientEntity = createTestIngredientEntity(ingredient);
    }

    @Test
    @DisplayName("Should create an ingredient successfully")
    void createIngredient() {
        ingredientService.createIngredient(ingredientDTO);
        assertEquals(ingredientDTO.ingredientId(), ingredientEntity.getIngredientId());
        verify(ingredientRepository).save(ingredientEntity);
    }
}