package com.example.comento.solution.controller;

import com.example.comento.auth.annotation.AuthenticationPrincipal;
import com.example.comento.auth.dto.response.Principal;
import com.example.comento.global.dto.ResponseDto;
import com.example.comento.solution.dto.response.SolutionDetailResponse;
import com.example.comento.solution.dto.response.SolutionListResponse;
import com.example.comento.solution.service.SolutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.comento.global.constant.PagingConstant.DEFAULT_PAGE_NUMBER;
import static com.example.comento.global.constant.PagingConstant.DEFAULT_SOLUTION_PAGE_SIZE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/solutions")
public class SolutionController {

    private final SolutionService solutionService;

    @GetMapping
    @Operation(summary = "풀이 목록 조회 api", description = "쿼리스트링을 적용하여 문제 별, 유저 별 풀이를 조회할 수 있다.")
    @Parameters({
            @Parameter(name = "page", description = "현재 페이지 번호", example = "0"),
            @Parameter(name = "size", description = "페이지 별 요소 출력 개수", example = "20"),
            @Parameter(name = "problem-id", description = "문제의 Id값. 문제의 Id는 long 타입임", example = "1"),
            @Parameter(name = "profile-id", description = "유저의 프로필 Id값. 이를 통해 특정 유저의 풀이 목록을 조회 가능", example = "636f6d2e-696e-7465-6c6c-696a2e646174")
    })
    public ResponseEntity<ResponseDto<SolutionListResponse>> findProblemSolution(@RequestParam(name = "problem-id", required = false) Long problemId,
                                                                                 @RequestParam(name = "size", defaultValue = DEFAULT_SOLUTION_PAGE_SIZE) int size,
                                                                                 @RequestParam(name = "page", defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                                                 @RequestParam(name = "profile-id", required= false) UUID userProfileId) {
        SolutionListResponse solutionListResponse = solutionService.findAllAboutProblem(problemId, page, size, userProfileId);
        return new ResponseEntity<>(ResponseDto.res(true, "풀이 목록 조회 성공", solutionListResponse), HttpStatus.OK);
    }

    @GetMapping("/{solution-id}")
    @Operation(summary = "풀이 상세 조회 api")
    public ResponseEntity<ResponseDto<SolutionDetailResponse>> findDetailSolution(@PathVariable(name = "solution-id") UUID solutionId) {
        SolutionDetailResponse solutionDetailResponse = solutionService.findSolution(solutionId);
        return new ResponseEntity<>(ResponseDto.res(true, "풀이 목록 조회 성공", solutionDetailResponse), HttpStatus.OK);
    }

    @GetMapping("/{solution-id}/ai-review")
    @Operation(summary = "솔루션에 대한 ai 리뷰 요청", description = "틀린 풀이의 경우 자동으로 ai리뷰가 생성되지 않음. 따라서 클라이언트가 원하는 경우 해당 api를 통해 ai리뷰를 받아볼 수 있도록 함.")
    public ResponseEntity<ResponseDto<SolutionDetailResponse>> getFailedAiReview(@PathVariable(name = "solution-id") UUID solutionId,
                                                               @AuthenticationPrincipal Principal principal){
        SolutionDetailResponse solutionDetailResponse = solutionService.getFailedAiReview(solutionId, principal);
        return new ResponseEntity<>(ResponseDto.res(true, "ai 리뷰 생성 성공", solutionDetailResponse), HttpStatus.OK);
    }

}
