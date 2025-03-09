package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.domain.dto.ProductDTO;
import com.ordermanagerwriter.application.exception.BusinessException;
import com.ordermanagerwriter.application.exception.CategoryNotFoundException;
import com.ordermanagerwriter.application.exception.ProductNotFoundException;
import com.ordermanagerwriter.application.domain.model.Product;
import com.ordermanagerwriter.application.service.ProductService;
import com.ordermanagerwriter.application.service.mapper.ProductMapper;
import com.ordermanagerwriter.infrastructure.repository.CategoryRepository;
import com.ordermanagerwriter.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void createProduct(ProductDTO product) {
        try {
            var categoryEntity = categoryRepository.findById(product.categoryId())
                    .orElseThrow(() -> new CategoryNotFoundException(product.categoryId()));
            var productEntity = ProductMapper.INSTANCE.dtoToEntity(product);
            productEntity.setCategory(categoryEntity);
            productRepository.save(productEntity);
        } catch (Exception e) {
            throw new BusinessException(String.format("Product creation failed: %s", e.getMessage()).toString());
        }
    }

    @Override
    public Product findProductById(String productId) {
        var productEntity = productRepository.findById(productId)
                .orElseThrow(() -> ProductNotFoundException.create(productId));
        return ProductMapper.INSTANCE.toModel(productEntity);
    }

    @Override
    public List<Product> findAllProducts() {
        var productEntities = productRepository.findAll();
        return ProductMapper.INSTANCE.toModels(productEntities);
    }

    @Override
    public void deleteProductById(String productId) {
        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            throw ProductNotFoundException.create(productId);
        }
    }
}
