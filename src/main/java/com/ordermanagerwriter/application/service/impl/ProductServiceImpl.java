package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.exception.BusinessException;
import com.ordermanagerwriter.application.model.Product;
import com.ordermanagerwriter.application.service.ProductService;
import com.ordermanagerwriter.application.service.mapper.ProductMapper;
import com.ordermanagerwriter.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ordermanagerwriter.AppConstants.ErrorMessages.PRODUCT_CREATION_FAILED;
import static com.ordermanagerwriter.AppConstants.ErrorMessages.PRODUCT_NOT_FOUND;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {
        try {
            var productEntity = ProductMapper.INSTANCE.toEntity(product);
            productRepository.save(productEntity);
        } catch (Exception e) {
            throw new BusinessException(PRODUCT_CREATION_FAILED + e.getMessage());
        }
    }

    @Override
    public Product findProductById(String productId) {
        var productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(PRODUCT_NOT_FOUND));
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
            throw new BusinessException(PRODUCT_NOT_FOUND);
        }
    }
}
