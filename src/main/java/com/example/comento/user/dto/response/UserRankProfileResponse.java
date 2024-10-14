package com.example.comento.user.dto.response;

import com.example.comento.user.dao.UserRankProfile;
import lombok.*;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserRankProfileResponse {
    private String name;
    private Long experience;
    private Long solvedCount;

    public static UserRankProfileResponse from(UserRankProfile rankProfileResponse){
        return new UserRankProfileResponse(rankProfileResponse.getName(), rankProfileResponse.getExperience(), rankProfileResponse.getSolvedCount());
    }
}
