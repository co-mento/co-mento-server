package com.example.comento.solution.repository;

import com.example.comento.solution.dao.SolutionDao;
import com.example.comento.solution.domain.Solution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SolutionJpaRepository extends JpaRepository<Solution, UUID> {

    @Query("select s.id as id, s.userProfile.name as userName, s.memory as memory, " +
            "s.time as time, s.language as language, s.isCorrect as isCorrect," +
            "s.problem.id as problemId, s.problem.title as problemTitle, s.createdAt as createdAt " +
            "from solution s " +
            "where s.problem.id = :problemId " +
            "order by s.createdAt desc, s.id ")
    Page<SolutionDao> findAllByProblemId(Long problemId, Pageable pageable);

    @Query("select s.id as id, s.userProfile.name as userName, s.memory as memory, " +
            "s.time as time, s.language as language, s.isCorrect as isCorrect," +
            "s.problem.id as problemId, s.problem.title as problemTitle, s.createdAt as createdAt " +
            "from solution s " +
            "where s.problem.id = :problemId and s.userProfile.id = :userProfileId " +
            "order by s.createdAt desc, s.id ")
    Page<SolutionDao> findAllByProblemIdAndUserProfileId(Long problemId, Pageable pageable, UUID userProfileId);
}
