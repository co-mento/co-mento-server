package com.example.comento.problem.controller;

import com.example.comento.auth.annotation.AuthenticationPrincipal;
import com.example.comento.auth.dto.response.Principal;
import com.example.comento.global.dto.ResponseDto;
import com.example.comento.like.service.ProblemLikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/problems")
public class ProblemController {

    private final ProblemLikeService problemLikeService;
    private final ProblemService problemService;

    @PostMapping
    @Operation(summary = "문제 작성 api")
    public ResponseEntity<ResponseDto<Problem>> createProblem(@RequestBody Problem problem) {
        Problem createdProblem = problemService.createProblem(problem);
        return new ResponseEntity<>(ResponseDto.res(true, "문제 작성 성공", createdProblem), HttpStatus.CREATED);
    }

    @PutMapping("/{problem-id}")
    @Operation(summary = "문제 수정 api")
    public ResponseEntity<ResponseDto<Problem>> updateProblem(@PathVariable("problem-id") Long problemId,
                                                              @RequestBody Problem updatedProblem) {
        Problem updated = problemService.updateProblem(problemId, updatedProblem);
        return new ResponseEntity<>(ResponseDto.res(true, "문제 수정 성공", updated), HttpStatus.OK);
    }

    @DeleteMapping("/{problem-id}")
    @Operation(summary = "문제 삭제 api")
    public ResponseEntity<ResponseDto<Void>> deleteProblem(@PathVariable("problem-id") Long problemId) {
        problemService.deleteProblem(problemId);
        return new ResponseEntity<>(ResponseDto.res(true, "문제 삭제 성공"), HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @Operation(summary = "모든 문제 조회 api")
    public ResponseEntity<ResponseDto<List<Problem>>> getAllProblems() {
        List<Problem> problems = problemService.getAllProblems();
        return new ResponseEntity<>(ResponseDto.res(true, "모든 문제 조회 성공", problems), HttpStatus.OK);
    }

    @GetMapping("/{problem-id}")
    @Operation(summary = "단일 문제 조회 api")
    public ResponseEntity<ResponseDto<Problem>> getProblem(@PathVariable("problem-id") Long problemId) {
        Problem problem = problemService.getProblem(problemId);
        return new ResponseEntity<>(ResponseDto.res(true, "문제 조회 성공", problem), HttpStatus.OK);
    }

    @PostMapping("/{problem-id}/like")
    @Operation(summary = "좋아요 추가 api")
    public ResponseEntity<ResponseDto<Void>> likeProblem(@AuthenticationPrincipal Principal principal,
                                                         @PathVariable("problem-id") Long problemId){
        problemLikeService.likeProblem(principal.getProfile(), problemId);
        return new ResponseEntity<>(ResponseDto.res(true, "좋아요 추가 성공"), HttpStatus.OK);
    }

    @DeleteMapping("/{problem-id}/like")
    @Operation(summary = "좋아요 삭제 api")
    public ResponseEntity<ResponseDto<Void>> unLikeProblem(@AuthenticationPrincipal Principal principal,
                                                           @PathVariable("problem-id") Long problemId){
        problemLikeService.unLikeProblem(principal.getProfile(), problemId);
        return new ResponseEntity<>(ResponseDto.res(true, "좋아요 삭제 성공"), HttpStatus.OK);
    }
}
