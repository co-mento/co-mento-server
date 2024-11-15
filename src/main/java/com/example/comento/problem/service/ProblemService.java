package com.example.comento.problem.service;

import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.problem.damain.Problem;
import com.example.comento.problem.dto.response.ProblemResponse;
import com.example.comento.problem.repository.ProblemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProblemService {

    private final ProblemJpaRepository problemJpaRepository;

    public Problem findById(Long problemId) {
        return problemJpaRepository.findById(problemId).orElseThrow(() ->
                new NotFoundException(ErrorCode.PROBLEM_NOT_FOUND));
    }

    @Transactional
    public Problem createProblem(Problem problem) {
        return problemJpaRepository.save(problem);
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

        return problemJpaRepository.save(existingProblem);
    }

    public ProblemResponse getProblem(Long problemId) {
        Problem problem = findById(problemId);
        return new ProblemResponse(
                problem.getId(),
                problem.getTitle(),
                problem.getContent(),
                problem.getInputExplain(),
                problem.getOutputExplain(),
                problem.getInputExample(),
                problem.getOutputExample(),
                problem.getTimeLimit(),
                problem.getMemoryLimit(),
                problem.getSource(),
                problem.getProblemCollectionList(),
                problem.getProblemCategoryList(),
                problem.getProblemLevelList()
        );
    }

    @Transactional
    public void deleteProblem(Long problemId) {
        Problem problem = findById(problemId);
        problemJpaRepository.delete(problem);
    }

    @Transactional(readOnly = true)
    public List<ProblemResponse> getAllProblems() {
        return problemJpaRepository.findAll().stream()
                .map(problem -> new ProblemResponse(
                        problem.getId(),
                        problem.getTitle(),
                        problem.getContent(),
                        problem.getInputExplain(),
                        problem.getOutputExplain(),
                        problem.getInputExample(),
                        problem.getOutputExample(),
                        problem.getTimeLimit(),
                        problem.getMemoryLimit(),
                        problem.getSource(),
                        problem.getProblemCollectionList(),
                        problem.getProblemCategoryList(),
                        problem.getProblemLevelList()
                ))
                .toList();
    }
}