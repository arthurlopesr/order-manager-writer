package com.ordermanagerwriter.application.service;

import com.ordermanagerwriter.application.model.Product;

import java.util.List;

public interface ProductService {
    void createProduct(Product product);
    Product findProductById(String productId);
    List<Product> findAllProducts();
    void deleteProductById(String productId);
}
