package com.example.comento.user.controller;

import com.example.comento.global.dto.ResponseDto;
import com.example.comento.user.dto.response.UserProfileResponse;
import com.example.comento.user.dto.response.UserRankingResponse;
import com.example.comento.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/ranking")
    @Operation(summary = "유저 랭킹 조회 api", description = "유저 경험치가 높은 순서 대로 정령, 경험치가 같은 경우 이름 순으로 정렬")
    public ResponseEntity<ResponseDto<UserRankingResponse>> getRanking(@RequestParam(defaultValue = "20") int size,
                                                                             @RequestParam(defaultValue = "0") int page){
        UserRankingResponse userRankingResponse = userService.getUserRanking(page, size);
        return new ResponseEntity<>(ResponseDto.res(true, "유저 랭킹 조회 성공", userRankingResponse), HttpStatus.OK);
    }



}
