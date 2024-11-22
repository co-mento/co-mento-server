package com.example.comento.problem.controller;

import com.example.comento.auth.annotation.AuthenticationPrincipal;
import com.example.comento.auth.dto.response.Principal;
import com.example.comento.global.dto.ResponseDto;
import com.example.comento.like.service.ProblemLikeService;
import com.example.comento.problem.damain.Problem;
import com.example.comento.problem.dto.response.ProblemPreviews;
import com.example.comento.problem.dto.response.ProblemDetailResponse;
import com.example.comento.problem.service.ProblemService;
import io.swagger.v3.oas.annotations.Operation;
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
public class ProblemController {

    private final ProblemLikeService problemLikeService;
    private final ProblemService problemService;

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
    @Operation(summary = "문제 목록 조회 api")
    public ResponseEntity<ResponseDto<ProblemPreviews>> getProblems(@AuthenticationPrincipal Principal principal,
                                                                    @RequestParam(name = "level", required = false) Long levelId,
                                                                    @RequestParam(name = "category", required = false) UUID categoryId,
                                                                    @RequestParam(name = "is-solved", required = false) Boolean isSolved,
                                                                    @RequestParam(name = "collection", required = false) UUID collectionId,
                                                                    @RequestParam(name = "keyword", required = false) String keyword,
                                                                    @RequestParam(name = "size", defaultValue = DEFAULT_SOLUTION_PAGE_SIZE) int size,
                                                                    @RequestParam(name = "page", defaultValue = DEFAULT_PAGE_NUMBER) int page) {
        ProblemPreviews problems = problemService.getProblems(size, page, principal, levelId, categoryId, isSolved, collectionId, keyword);
        return new ResponseEntity<>(ResponseDto.res(true, "문제 목록 조회 성공", problems), HttpStatus.OK);
    }

    @GetMapping("/{problem-id}")
    @Operation(summary = "단일 문제 조회 api")
    public ResponseEntity<ResponseDto<ProblemDetailResponse>> getProblem(@PathVariable("problem-id") Long problemId, @AuthenticationPrincipal Principal principal) {
        ProblemDetailResponse problem = problemService.getDetailProblem(problemId, principal);
        return new ResponseEntity<>(ResponseDto.res(true, "문제 상세 조회 성공", problem), HttpStatus.OK);
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