package com.example.comento.problem.service;

import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.problem.damain.Problem;
import com.example.comento.problem.repository.ProblemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProblemService {

    private final ProblemJpaRepository problemJpaRepository;

    public Problem findById(Long problemId) {
        return problemJpaRepository.findById(problemId).orElseThrow(() ->
                new NotFoundException(ErrorCode.PROBLEM_NOT_FOUND));
    }
}
