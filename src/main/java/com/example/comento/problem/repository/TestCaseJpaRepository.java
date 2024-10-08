package com.example.comento.problem.repository;

import com.example.comento.problem.damain.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestCaseJpaRepository extends JpaRepository<TestCase, UUID> {
}
