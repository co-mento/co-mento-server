package com.example.comento.admin;

import com.example.comento.admin.annotation.ExistCategories;
import com.example.comento.global.dto.ResponseDto;
import com.example.comento.problem.damain.Problem;
import com.example.comento.problem.dto.request.ProblemRegisterRequest;
import com.example.comento.problem.service.ProblemService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final ProblemService problemService;

    @PostMapping("/problems")
    @Operation(summary = "문제 등록 api")
    public ResponseEntity<ResponseDto<Void>> createProblem(@RequestBody @ExistCategories  ProblemRegisterRequest problemRegisterRequest) {
        problemService.createProblem(problemRegisterRequest);
        return new ResponseEntity<>(ResponseDto.res(true, "문제 작성 성공"), HttpStatus.CREATED);
    }
}
