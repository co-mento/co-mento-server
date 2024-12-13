package com.example.comento.category.repository;

import com.example.comento.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, UUID> {
    public Optional<Category> findCategoryByName(String name);

    public boolean existsByName(String name);
}
