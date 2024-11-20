package com.example.comento.solvedstatus.repository;

import com.example.comento.solvedstatus.domain.SolvedStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SolvedStatusRepository extends JpaRepository<SolvedStatus, UUID> {
}
