package com.example.comento.user.service;

import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.problem.repository.problem.ProblemRepository;
import com.example.comento.solution.dao.ProblemId;
import com.example.comento.solution.dto.response.ProblemIdsResponse;
import com.example.comento.solution.service.SolutionService;
import com.example.comento.user.domain.User;
import com.example.comento.user.domain.UserProfile;
import com.example.comento.user.dto.response.DetailUserProfileResponse;
import com.example.comento.user.dto.response.UserProfileResponse;
import com.example.comento.user.repository.UserProfileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileJpaRepository userProfileJpaRepository;
    private final ProblemRepository problemRepository;

    public DetailUserProfileResponse getDetailProfile(UUID userProfileId){
        UserProfile userProfile = findById(userProfileId);

        Long experienceGreaterThanUser = userProfileJpaRepository.
                countUserProfilesByExperienceGreaterThan(userProfile.getExperience());

        ProblemIdsResponse solvedProblemIds = userSolvedList(userProfile);
        ProblemIdsResponse failedProblemIds = userFailedList(userProfile);
        ProblemIdsResponse likedProblemIds = userLikedList(userProfile);

        return DetailUserProfileResponse.from(userProfile,
                solvedProblemIds, failedProblemIds, likedProblemIds,
                experienceGreaterThanUser + 1);
    }

    public UserProfileResponse getProfileResponse(User user){
        UserProfile userProfile = findByUser(user);
        return UserProfileResponse.from(userProfile);
    }

    public UserProfile findByUser(User user){
        return userProfileJpaRepository.findByUser(user).orElseThrow(()->
                new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    public UserProfile findById(UUID profileId){
        return userProfileJpaRepository.findById(profileId).orElseThrow(()->
                new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    private ProblemIdsResponse userLikedList(UserProfile userprofile) {
        List<ProblemId> problemIdList = problemRepository.getLikedProblemList(userprofile);
        return ProblemIdsResponse.from(problemIdList);
    }

    public ProblemIdsResponse userSolvedList(UserProfile userProfile) {
        List<ProblemId> problemIdList = problemRepository.getSolvedProblemList(userProfile);
        return ProblemIdsResponse.from(problemIdList);
    }

    public ProblemIdsResponse userFailedList(UserProfile userProfile) {
        List<ProblemId> problemIdList = problemRepository.getFailedProblemList(userProfile);
        return ProblemIdsResponse.from(problemIdList);
    }
}
