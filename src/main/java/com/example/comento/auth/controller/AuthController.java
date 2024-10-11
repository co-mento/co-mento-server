package com.example.comento.auth.controller;

import com.example.comento.auth.dto.request.SignUpRequest;
import com.example.comento.auth.service.AuthService;
import com.example.comento.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto<Void>> signUp(@Valid @RequestBody SignUpRequest request){
        authService.signUp(request);
        return new ResponseEntity<>(ResponseDto.res(true, "회원가입 성공 "), HttpStatus.CREATED);
    }


}
