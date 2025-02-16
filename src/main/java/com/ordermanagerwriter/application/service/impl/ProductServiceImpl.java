package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.exception.BusinessException;
import com.ordermanagerwriter.application.model.Product;
import com.ordermanagerwriter.application.service.ProductService;
import com.ordermanagerwriter.application.service.mapper.ProductMapper;
import com.ordermanagerwriter.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

import static com.ordermanagerwriter.AppConstants.ErrorMessages.NOT_FOUND;

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
            throw new BusinessException(MessageFormat.format("Product creation failed: %s", e.getMessage()).toString());
        }
    }

    @Override
    public Product findProductById(String productId) {
        var productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(MessageFormat.format(NOT_FOUND, "Product")));
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
            throw new BusinessException(MessageFormat.format(NOT_FOUND, "Product"));
        }
    }
}
