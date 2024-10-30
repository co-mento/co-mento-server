package com.example.comento.like.service;

import com.example.comento.global.exception.ConflictException;
import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.like.domain.ProblemLike;
import com.example.comento.like.repository.ProblemLikeJpaRepository;
import com.example.comento.problem.damain.Problem;
import com.example.comento.problem.service.ProblemService;
import com.example.comento.user.domain.UserProfile;
import com.example.comento.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemLikeService {
    private final ProblemService problemService;
    private final ProblemLikeJpaRepository problemLikeJpaRepository;

    public void likeProblem(UserProfile userProfile, Long problemId) {
        Problem problem = problemService.findById(problemId);
        ProblemLike problemLike = new ProblemLike(userProfile, problem);

        try {
            problemLikeJpaRepository.save(problemLike);
        }catch (DataIntegrityViolationException e){
            throw new ConflictException(ErrorCode.LIKE_CONFLICT, "이미 좋아요를 누른 문제입니다.");
        }
    }

    public void unLikeProblem(UserProfile userProfile, Long problemId) {
        Problem problem = problemService.findById(problemId);
        ProblemLike problemLike = problemLikeJpaRepository.findByUserProfileAndProblem(userProfile, problem)
                .orElseThrow(()->new ConflictException(ErrorCode.LIKE_CONFLICT));
        problemLikeJpaRepository.delete(problemLike);
    }

}
