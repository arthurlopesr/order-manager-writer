package com.ordermanagerwriter.application.service;

import com.ordermanagerwriter.application.domain.dto.ProductDTO;
import com.ordermanagerwriter.application.domain.model.Product;

import java.util.List;

public interface ProductService {
    void createProduct(ProductDTO product);
    Product findProductById(String productId);
    List<Product> findAllProducts();
    void deleteProductById(String productId);
}
