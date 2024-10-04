package com.example.comento.repository;

import com.example.comento.domain.collection.ProblemCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProblemCollectionJpaRepository extends JpaRepository<ProblemCollection, UUID> {
}
