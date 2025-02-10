package com.ordermanagerwriter.infrastructure.repository;

import com.ordermanagerwriter.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
