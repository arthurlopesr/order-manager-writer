package com.ordermanagerwriter.infrastructure.controller;

import com.ordermanagerwriter.application.domain.dto.ProductDTO;
import com.ordermanagerwriter.application.domain.model.Product;
import com.ordermanagerwriter.application.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDTO product) {
        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable("productId") String productId) {
        var product = productService.findProductById(productId);
        return ResponseEntity.status(HttpStatus.OK.value()).body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        var products = productService.findAllProducts();
        return ResponseEntity.status(HttpStatus.OK.value()).body(products);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("productId") String productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
    }
}
