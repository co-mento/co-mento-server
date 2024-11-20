package com.example.comento.level.repository;

import com.example.comento.level.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LevelJpaRepository extends JpaRepository<Level, Long> {
}
