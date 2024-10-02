package com.example.comento.repository;

import com.example.comento.domain.problem.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProblemJpaRepository extends JpaRepository<Problem, UUID> {
}
