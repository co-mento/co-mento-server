package com.example.comento.like.repository;

import com.example.comento.like.domain.ProblemLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProblemLikeJpaRepository extends JpaRepository<ProblemLike, UUID> {
}
