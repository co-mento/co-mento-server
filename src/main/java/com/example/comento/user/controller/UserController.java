package com.example.comento.user.controller;

import com.example.comento.global.dto.ResponseDto;
import com.example.comento.user.domain.UserProfile;
import com.example.comento.user.dto.response.DetailUserProfileResponse;
import com.example.comento.user.dto.response.UserRankingResponse;
import com.example.comento.user.service.UserProfileService;
import com.example.comento.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.comento.global.constant.PagingConstant.DEFAULT_PAGE_NUMBER;
import static com.example.comento.global.constant.PagingConstant.DEFAULT_RANKING_PAGE_SIZE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserProfileService userProfileService;

    @GetMapping("/ranking")
    @Operation(summary = "유저 랭킹 조회 api", description = "유저 경험치가 높은 순서 대로 정렬, 경험치가 같은 경우 이름 순으로 정렬")
    public ResponseEntity<ResponseDto<UserRankingResponse>> getRanking(@RequestParam(defaultValue = DEFAULT_RANKING_PAGE_SIZE) int size,
                                                                             @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page){
        UserRankingResponse userRankingResponse = userService.getUserRanking(page, size);
        return new ResponseEntity<>(ResponseDto.res(true, "유저 랭킹 조회 성공", userRankingResponse), HttpStatus.OK);
    }

    @GetMapping("/{userprofile-id}")
    @Operation(summary = "유저 프로필 조회 api")
    public ResponseEntity<ResponseDto<DetailUserProfileResponse>> getDetailProfile(
            @PathVariable("userprofile-id")UUID userProfileId){
        DetailUserProfileResponse detailProfile = userProfileService.getDetailProfile(userProfileId);
        return new ResponseEntity<>(ResponseDto.res(true, "유저 프로필 상세 조회 성공", detailProfile), HttpStatus.OK);
    }

}
