package com.example.comento.user.dto.response;


import com.example.comento.global.dto.PaginationResponse;
import com.example.comento.user.domain.UserProfile;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserRankingResponse {
    private List<UserProfileResponse> userProfileRequests;
    private PaginationResponse paginationResponse;

    public static UserRankingResponse from(Page<UserProfile> profiles){
        return new UserRankingResponse(
                profiles.map(UserProfileResponse::from).stream().collect(Collectors.toList()),
                PaginationResponse.from(profiles));
    }
}
