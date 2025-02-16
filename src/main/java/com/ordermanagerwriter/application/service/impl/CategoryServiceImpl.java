package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.exception.BusinessException;
import com.ordermanagerwriter.application.model.Category;
import com.ordermanagerwriter.application.service.CategoryService;
import com.ordermanagerwriter.application.service.mapper.CategoryMapper;
import com.ordermanagerwriter.infrastructure.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

import static com.ordermanagerwriter.AppConstants.ErrorMessages.NOT_FOUND;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void createCategory(Category category) {
        try {
            var categoryEntity = CategoryMapper.INSTANCE.toEntity(category);
            categoryRepository.save(categoryEntity);
        } catch (Exception e) {
            throw new BusinessException(MessageFormat.format("Category creation failed: %s", e.getMessage()).toString());
        }
    }

    @Override
    public Category findCategoryById(String categoryId) {
        var categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(MessageFormat.format(NOT_FOUND, "Category")));
        return CategoryMapper.INSTANCE.toModel(categoryEntity);
    }

    @Override
    public List<Category> findAllCategories() {
        var categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return List.of();
        }
        return CategoryMapper.INSTANCE.toModelList(categories);
    }

    @Override
    public void deleteCategoryById(String categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
        } catch (Exception e) {
            throw new BusinessException(MessageFormat.format(NOT_FOUND, "Category"));
        }
    }
}
