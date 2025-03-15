package com.ordermanagerwriter.infrastructure.controller;

import com.ordermanagerwriter.application.domain.dto.CategoryDTO;
import com.ordermanagerwriter.application.domain.model.Category;
import com.ordermanagerwriter.application.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO category) {
        var categorySaved = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(categorySaved);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findCategoryById(@PathVariable("categoryId") String categoryId) {
        var category = categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAllCategories() {
        var categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("categoryId") String categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }
}
