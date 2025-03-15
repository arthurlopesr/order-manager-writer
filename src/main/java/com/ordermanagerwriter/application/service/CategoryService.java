package com.ordermanagerwriter.application.service;

import com.ordermanagerwriter.application.domain.dto.CategoryDTO;
import com.ordermanagerwriter.application.domain.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDTO category);
    Category findCategoryById(String categoryId);
    List<Category> findAllCategories();
    void deleteCategoryById(String categoryId);
}
