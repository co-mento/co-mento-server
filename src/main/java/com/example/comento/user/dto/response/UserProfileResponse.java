package com.example.comento.user.dto.response;

import com.example.comento.user.domain.UserProfile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileResponse {
    private final UUID userProfileId;
    private final String name;

    public static UserProfileResponse from(UserProfile userProfile){
        return new UserProfileResponse(userProfile.getId(), userProfile.getName());
    }

}
