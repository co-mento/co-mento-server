package com.example.comento.user.dto.response;

import com.example.comento.solution.dto.response.ProblemIdsResponse;
import com.example.comento.user.domain.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DetailUserProfileResponse {
    private final String name;
    private final Long experience;
    private final Long ranking;
    private final List<Long> solvedProblemIds;
    private final List<Long> failedProblemIds;
    private final List<Long> likedProblemIds;

    public static DetailUserProfileResponse from(
            UserProfile profile,
            ProblemIdsResponse solvedProblemIds,
            ProblemIdsResponse failedProblemIds,
            ProblemIdsResponse likedProblemIds,
            Long ranking){

        return new DetailUserProfileResponse(
                profile.getName(),
                profile.getExperience(),
                ranking,
                solvedProblemIds.getProblemIds(),
                failedProblemIds.getProblemIds(),
                likedProblemIds.getProblemIds());
    }
}
