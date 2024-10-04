package com.example.comento.repository;

import com.example.comento.domain.category.ProblemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProblemCategoryJpaRepository extends JpaRepository<ProblemCategory, UUID> {
}
