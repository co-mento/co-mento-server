package com.example.comento.repository;

import com.example.comento.domain.level.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LevelJpaRepository extends JpaRepository<Level, UUID> {
}
