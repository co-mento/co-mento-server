package com.example.comento.user.repository;

import com.example.comento.user.domain.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileJpaRepository extends JpaRepository<UserProfile, UUID> {

    @Query("select up " +
            "from user_profile as up " +
            "order by up.experience DESC, up.name ")
    public Page<UserProfile> getUserRanking(Pageable pageable);
}
