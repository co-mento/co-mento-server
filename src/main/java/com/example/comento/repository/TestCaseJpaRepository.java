package com.example.comento.repository;

import com.example.comento.domain.problem.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestCaseJpaRepository extends JpaRepository<TestCase, UUID> {
}
