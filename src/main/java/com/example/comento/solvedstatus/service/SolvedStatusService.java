package com.example.comento.solvedstatus.service;

import com.example.comento.problem.damain.Problem;
import com.example.comento.solvedstatus.domain.SolvedStatus;
import com.example.comento.solvedstatus.repository.SolvedStatusRepository;
import com.example.comento.user.domain.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolvedStatusService {
    private final SolvedStatusRepository solvedStatusRepository;

    public void registerSolvedStatus(UserProfile profile, Problem problem, Boolean isSolved){
        if(solvedStatusRepository.existsByUserProfileAndProblem(profile, problem)) {
            SolvedStatus solvedStatus = find(profile, problem);
            //기존 false였고 이번에 맞았다면 True로 설정.
            if (!solvedStatus.getFlag() && isSolved) {
                solvedStatus.setTrue();
                solvedStatusRepository.save(solvedStatus);
            }
        }else {
            SolvedStatus solvedStatus = new SolvedStatus(profile, problem, isSolved);
            solvedStatusRepository.save(solvedStatus);
        }
    }

    public SolvedStatus find(UserProfile profile, Problem problem){
        return solvedStatusRepository.findByUserProfileAndProblem(profile, problem).orElseGet(null);
    }

    public boolean isSolvedStatusTrue(UserProfile userProfile, Problem problem){
        return solvedStatusRepository.existsByUserProfileAndProblemAndFlagIsTrue(userProfile, problem);
    }
}
