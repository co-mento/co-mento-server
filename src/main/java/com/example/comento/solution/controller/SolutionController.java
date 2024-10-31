package com.example.comento.solution.controller;

import com.example.comento.auth.annotation.AuthenticationPrincipal;
import com.example.comento.auth.dto.response.Principal;
import com.example.comento.global.dto.ResponseDto;
import com.example.comento.solution.dto.response.SolutionListResponse;
import com.example.comento.solution.service.SolutionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.comento.global.constant.PagingConstant.DEFAULT_PAGE_NUMBER;
import static com.example.comento.global.constant.PagingConstant.DEFAULT_SOLUTION_PAGE_SIZE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/problems")
public class SolutionController {

    private final SolutionService solutionService;

    @GetMapping("/{problem-id}/solutions")
    @Operation(summary = "문제 별 풀이 목록 조회 api")
    public ResponseEntity<ResponseDto<SolutionListResponse>> findProblemSolution(@PathVariable("problem-id") Long problemId,
                                                                                 @RequestParam(name = "size", defaultValue = DEFAULT_SOLUTION_PAGE_SIZE) int size,
                                                                                 @RequestParam(name = "page", defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                                                 @RequestParam(name = "profile-id", required= false) UUID userProfileId) {
        SolutionListResponse solutionListResponse = solutionService.findAllAboutProblem(problemId, page, size, userProfileId);
        return new ResponseEntity<>(ResponseDto.res(true, "풀이 목록 조회 성공", solutionListResponse), HttpStatus.OK);
    }
}
