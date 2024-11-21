package com.example.comento.problem.service;

import com.example.comento.auth.dto.response.Principal;
import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.problem.damain.Problem;
import com.example.comento.problem.dto.response.ProblemDetailInformation;
import com.example.comento.problem.dto.response.ProblemPreviews;
import com.example.comento.problem.dto.response.ProblemDetailResponse;
import com.example.comento.problem.repository.problem.ProblemRepository;
import com.example.comento.solution.repository.SolutionJpaRepository;
import com.example.comento.solvedstatus.domain.SolvedStatus;
import com.example.comento.solvedstatus.repository.SolvedStatusRepository;
import com.example.comento.user.domain.UserProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final SolutionJpaRepository solutionJpaRepository;
    private final SolvedStatusRepository solvedStatusRepository;

    public  ProblemPreviews getProblems(int size,
                                       int page,
                                       Principal principal,
                                       Long levelId,
                                       UUID categoryId,
                                       Boolean isSolved,
                                       UUID collectionId,
                                       String keyword){

        Pageable pageable = PageRequest.of(page, size);
        UserProfile profile = principal.getProfile();
        Page<ProblemDetailInformation> problemPreviews = problemRepository.getProblemPreviews(pageable, profile, levelId, categoryId, isSolved, collectionId, keyword);

        return ProblemPreviews.from(problemPreviews);
    }

    public Problem findById(Long problemId) {
        return problemRepository.findById(problemId).orElseThrow(() ->
                new NotFoundException(ErrorCode.PROBLEM_NOT_FOUND));
    }

    @Transactional
    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    @Transactional
    public Problem updateProblem(Long problemId, Problem updatedProblem) {
        Problem existingProblem = findById(problemId);

        existingProblem.setTitle(updatedProblem.getTitle());
        existingProblem.setContent(updatedProblem.getContent());
        existingProblem.setInputExplain(updatedProblem.getInputExplain());
        existingProblem.setOutputExplain(updatedProblem.getOutputExplain());
        existingProblem.setInputExample(updatedProblem.getInputExample());
        existingProblem.setOutputExample(updatedProblem.getOutputExample());
        existingProblem.setTimeLimit(updatedProblem.getTimeLimit());
        existingProblem.setMemoryLimit(updatedProblem.getMemoryLimit());
        existingProblem.setSource(updatedProblem.getSource());

        return problemRepository.save(existingProblem);
    }

    public ProblemDetailResponse getDetailProblem(Long problemId, Principal principal) {
        Problem problem = findById(problemId);
        UserProfile userProfile = principal.getProfile();
        ProblemDetailInformation problemDetailInformation =  new ProblemDetailInformation(problem, false);
        
        if(userProfile != null){
            Boolean hasSolved = solutionJpaRepository.isUserSolved(userProfile, problem);
            problemDetailInformation = new ProblemDetailInformation(problem, hasSolved);
        }

        Long numberOfProblemSolution = solutionJpaRepository.countAllByProblem(problem);
        Long numberOfCorrect = solvedStatusRepository.countAllByProblemAndFlag(problem, Boolean.TRUE);
        Double correctRate = numberOfProblemSolution > 0 ?
                (numberOfCorrect.doubleValue() / numberOfProblemSolution.doubleValue()) * 100 : 0.0;

        BigDecimal roundedCorrectRate = new BigDecimal(correctRate).setScale(3, RoundingMode.HALF_UP);

        return ProblemDetailResponse.from(problemDetailInformation,
                numberOfProblemSolution,
                numberOfCorrect,
                roundedCorrectRate.doubleValue());
    }

    @Transactional
    public void deleteProblem(Long problemId) {
        Problem problem = findById(problemId);
        problemRepository.delete(problem);
    }

}