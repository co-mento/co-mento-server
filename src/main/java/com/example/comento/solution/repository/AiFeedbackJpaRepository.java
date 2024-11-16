package com.example.comento.solution.repository;

import com.example.comento.solution.domain.AiFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AiFeedbackJpaRepository extends JpaRepository<AiFeedback, UUID> {
}
