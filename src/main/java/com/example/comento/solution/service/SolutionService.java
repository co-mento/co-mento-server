package com.example.comento.solution.service;

import com.example.comento.problem.repository.ProblemJpaRepository;
import com.example.comento.solution.dao.ProblemId;
import com.example.comento.solution.dao.SolutionDao;
import com.example.comento.solution.dto.response.ProblemIdsResponse;
import com.example.comento.solution.dto.response.SolutionListResponse;
import com.example.comento.solution.repository.SolutionJpaRepository;
import com.example.comento.user.domain.UserProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SolutionService {
    private final ProblemJpaRepository problemJpaRepository;
    private final SolutionJpaRepository solutionJpaRepository;

    public ProblemIdsResponse userSolvedList(UserProfile userProfile) {
        List<ProblemId> problemIdList = problemJpaRepository.getSolvedProblemList(userProfile);
        return ProblemIdsResponse.from(problemIdList);
    }

    public ProblemIdsResponse userFailedList(UserProfile userProfile) {
        List<ProblemId> problemIdList = problemJpaRepository.getFailedProblemList(userProfile);
        return ProblemIdsResponse.from(problemIdList);
    }

    public ProblemIdsResponse userLikedList(UserProfile userprofile) {
        List<ProblemId> problemIdList = problemJpaRepository.getLikedProblemList(userprofile);
        return ProblemIdsResponse.from(problemIdList);
    }

    public SolutionListResponse findAllAboutProblem(Long problemId, int page, int size, UUID userProfileId) {
        Pageable pageable = PageRequest.of(page, size);

        if (userProfileId == null && problemId == null) {
            Page<SolutionDao> solutionDaos = solutionJpaRepository.findAllSolution(pageable);
            return SolutionListResponse.from(solutionDaos);
        }
        if (userProfileId == null) {
            Page<SolutionDao> solutionDaos = solutionJpaRepository.findAllByProblemId(problemId, pageable);
            return SolutionListResponse.from(solutionDaos);
        }
        if (problemId == null) {
            Page<SolutionDao> solutionDaos = solutionJpaRepository.findAllByProfileId(userProfileId, pageable);
            return SolutionListResponse.from(solutionDaos);
        }

        Page<SolutionDao> solutionDaos = solutionJpaRepository.findAllByProblemIdAndUserProfileId(problemId, userProfileId, pageable);
        return SolutionListResponse.from(solutionDaos);
    }
}
