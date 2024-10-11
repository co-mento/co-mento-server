package com.example.comento.collection.repository;

import com.example.comento.collection.domain.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CollectionJpaRepository extends JpaRepository<Collection, UUID> {
}
