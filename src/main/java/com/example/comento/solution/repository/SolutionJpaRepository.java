package com.example.comento.solution.repository;

import com.example.comento.solution.domain.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SolutionJpaRepository extends JpaRepository<Solution, UUID> {
}
