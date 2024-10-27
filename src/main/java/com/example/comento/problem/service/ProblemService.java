package com.example.comento.problem.service;

import com.example.comento.problem.repository.ProblemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProblemService {

    private final ProblemJpaRepository problemJpaRepository;
}
