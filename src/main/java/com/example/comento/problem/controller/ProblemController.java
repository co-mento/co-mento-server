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
