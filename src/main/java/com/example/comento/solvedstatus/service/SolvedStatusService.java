package com.example.comento.solvedstatus.service;

import com.example.comento.solvedstatus.repository.SolvedStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolvedStatusService {
    private final SolvedStatusRepository solvedStatusRepository;
}
