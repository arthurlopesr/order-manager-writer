package com.ordermanagerwriter.application.service;

import com.ordermanagerwriter.application.domain.model.Category;

import java.util.List;

public interface CategoryService {
    void createCategory(Category category);
    Category findCategoryById(String categoryId);
    List<Category> findAllCategories();
    void deleteCategoryById(String categoryId);
}
