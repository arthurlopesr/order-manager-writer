package com.ordermanagerwriter.application.service.impl;

import com.ordermanagerwriter.application.exception.BusinessException;
import com.ordermanagerwriter.application.model.Product;
import com.ordermanagerwriter.application.service.ProductService;
import com.ordermanagerwriter.application.service.mapper.ProductMapper;
import com.ordermanagerwriter.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ordermanagerwriter.AppConstants.ErrorMessages.PRODUCT_CREATION_FAILED;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public void createProduct(Product product) {
        try {
            var productEntity = ProductMapper.INSTANCE.toEntity(product);
            productRepository.save(productEntity);
        } catch (Exception e) {
            throw new BusinessException(PRODUCT_CREATION_FAILED + e.getMessage());
        }
    }
}
