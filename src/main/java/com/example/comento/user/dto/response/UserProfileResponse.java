package com.example.comento.user.dto.response;

import com.example.comento.user.domain.UserProfile;
import lombok.*;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserProfileResponse {
    private String name;
    private Long experience;

    public static UserProfileResponse from(UserProfile userProfile){
        return new UserProfileResponse(userProfile.getName(), userProfile.getExperience());
    }
}
