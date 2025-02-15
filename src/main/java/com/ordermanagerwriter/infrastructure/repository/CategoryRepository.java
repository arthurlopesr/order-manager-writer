package com.ordermanagerwriter.infrastructure.repository;

import com.ordermanagerwriter.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
}
