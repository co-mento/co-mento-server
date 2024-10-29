package com.example.comento.problem.repository;

import com.example.comento.problem.damain.Problem;
import com.example.comento.solution.dao.ProblemId;
import com.example.comento.user.domain.UserProfile;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProblemJpaRepository extends JpaRepository<Problem, Long> {

    @Query("select p.id as id " +
            "from problem as p " +
            "left join solution as s on p.id = s.problem.id " +
            "where s.isCorrect = true and s.userProfile = :profile " +
            "group by p.id " +
            "order by p.id desc")
    List<ProblemId> getSolvedProblemList(@NonNull @Param("profile") UserProfile profile);

    @Query("select p.id as id " +
            "from problem as p " +
            "where exists ( " +
            "   select 1 " +
            "   from solution as s " +
            "   where s.userProfile = :profile and s.problem = p and s.isCorrect = false" +
            ")" +
            "and not exists (" +
            "   select 1 " +
            "   from solution s " +
            "   where s.userProfile = :profile and s.problem = p and s.isCorrect = true " +
            ")" +
            "group by p.id " +
            "order by p.id desc")
    List<ProblemId> getFailedProblemList(UserProfile profile);

    @Query ("select p.id as id " +
            "from problem as p " +
            "where EXISTS (" +
            "   select 1 " +
            "   from problem_like as pl " +
            "   where pl.problem = p and pl.userProfile = :profile " +
            ")" +
            "order by p.id desc")
    List<ProblemId> getLikedProblemList(UserProfile profile);
}
