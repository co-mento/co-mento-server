package com.example.comento.user.dto.response;


import com.example.comento.global.dto.PaginationResponse;
import com.example.comento.user.dao.UserRankProfile;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserRankingResponse {
    private List<UserRankProfileResponse> userProfileRequests;
    private PaginationResponse paginationResponse;

    public static UserRankingResponse from(Page<UserRankProfile> profiles){
        return new UserRankingResponse(
                profiles.map(UserRankProfileResponse::from).stream().collect(Collectors.toList()),
                PaginationResponse.from(profiles));
    }
}
