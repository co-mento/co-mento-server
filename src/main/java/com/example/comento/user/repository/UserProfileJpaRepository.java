package com.example.comento.user.repository;

import com.example.comento.user.dao.UserRankProfile;
import com.example.comento.user.domain.User;
import com.example.comento.user.domain.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileJpaRepository extends JpaRepository<UserProfile, UUID> {

    Optional<UserProfile> findByUser(User user);

    @Query("select up.name as name, up.experience as experience, count(s) as solvedCount " +
            "from user_profile as up " +
            "left join solution as s on up.id = s.userProfile.id and s.isCorrect = true " +
            "group by up.id " +
            "order by up.experience DESC, up.name ")
    Page<UserRankProfile> getUserRanking(Pageable pageable);

    Long countUserProfilesByExperienceGreaterThan(Long experience);
}
