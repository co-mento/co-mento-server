package com.example.comento.solvedstatus.repository;

import com.example.comento.problem.damain.Problem;
import com.example.comento.solvedstatus.domain.SolvedStatus;
import com.example.comento.user.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SolvedStatusRepository extends JpaRepository<SolvedStatus, UUID> {

    public Optional<SolvedStatus> findSolvedStatusByProblemAndUserProfile(Problem problem, UserProfile profile);
    public Long countAllByProblemAndFlag(Problem problem, Boolean flag);
}
