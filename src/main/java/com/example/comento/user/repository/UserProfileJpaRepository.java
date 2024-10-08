package com.example.comento.user.repository;

import com.example.comento.user.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileJpaRepository extends JpaRepository<UserProfile, UUID> {
}
