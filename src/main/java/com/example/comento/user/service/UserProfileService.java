package com.example.comento.user.service;

import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.solution.dao.ProblemId;
import com.example.comento.solution.dto.response.ProblemIdsResponse;
import com.example.comento.solution.service.SolutionService;
import com.example.comento.user.domain.User;
import com.example.comento.user.domain.UserProfile;
import com.example.comento.user.dto.response.DetailUserProfileResponse;
import com.example.comento.user.repository.UserProfileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final SolutionService solutionService;
    private final UserProfileJpaRepository userProfileJpaRepository;

    public DetailUserProfileResponse getDetailProfile(UUID userProfileId){
        UserProfile userProfile = findById(userProfileId);
        ProblemIdsResponse solvedProblemIds = solutionService.userSolvedList(userProfile);
        ProblemIdsResponse failedProblemIds = solutionService.userFailedList(userProfile);
        return DetailUserProfileResponse.from(userProfile, solvedProblemIds, failedProblemIds);
    }

    public UserProfile findByUser(User user){
        return userProfileJpaRepository.findByUser(user).orElseThrow(()->
                new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    public UserProfile findById(UUID profileId){
        return userProfileJpaRepository.findById(profileId).orElseThrow(()->
                new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
