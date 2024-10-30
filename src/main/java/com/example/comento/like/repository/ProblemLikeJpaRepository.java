package com.example.comento.like.repository;

import com.example.comento.like.domain.ProblemLike;
import com.example.comento.problem.damain.Problem;
import com.example.comento.user.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProblemLikeJpaRepository extends JpaRepository<ProblemLike, UUID> {

    Optional<ProblemLike> findByUserProfileAndProblem(UserProfile userProfile, Problem problem);
}
