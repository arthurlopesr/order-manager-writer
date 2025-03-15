package com.ordermanagerwriter.infrastructure.controller;

import com.ordermanagerwriter.application.domain.dto.IngredientDTO;
import com.ordermanagerwriter.application.domain.model.Ingredient;
import com.ordermanagerwriter.application.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredient")
@AllArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody IngredientDTO ingredientDTO) {
        var ingredientSaved = ingredientService.createIngredient(ingredientDTO);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(ingredientSaved);
    }
}
