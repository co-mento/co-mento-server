package com.example.comento.solution.service;

import com.example.comento.problem.repository.ProblemJpaRepository;
import com.example.comento.solution.dao.ProblemId;
import com.example.comento.solution.dto.response.ProblemIdsResponse;
import com.example.comento.user.domain.UserProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SolutionService {
    private final ProblemJpaRepository problemJpaRepository;

    public ProblemIdsResponse userSolvedList(UserProfile userProfile){
        List<ProblemId> problemIdList = problemJpaRepository.getSolvedProblemList(userProfile);
        return ProblemIdsResponse.from(problemIdList);
    }

    public ProblemIdsResponse userFailedList(UserProfile userProfile){
        List<ProblemId> problemIdList = problemJpaRepository.getFailedProblemList(userProfile);
        return ProblemIdsResponse.from(problemIdList);
    }

    public ProblemIdsResponse userLikedList(UserProfile userprofile){
        List<ProblemId> problemIdList = problemJpaRepository.getLikedProblemList(userprofile);
        return ProblemIdsResponse.from(problemIdList);
    }
}
