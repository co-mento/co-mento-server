package com.example.comento.collection.repository;

import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CollectionJpaRepository extends JpaRepository<Collation, UUID> {
}
