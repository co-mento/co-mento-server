package com.example.comento.auth.controller;

import com.example.comento.auth.dto.request.LoginRequest;
import com.example.comento.auth.dto.request.SignUpRequest;
import com.example.comento.auth.dto.response.TokenResponseCookies;
import com.example.comento.auth.service.AuthService;
import com.example.comento.auth.service.TokenService;
import com.example.comento.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입 api")
    public ResponseEntity<ResponseDto<Void>> signUp(@Valid @RequestBody SignUpRequest request){
        authService.signUp(request);
        return new ResponseEntity<>(ResponseDto.res(true, "회원가입 성공"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 api")
    public ResponseEntity<ResponseDto<Void>> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response){
        UUID id = authService.login(request);
        TokenResponseCookies cookies = tokenService.issueToken(id.toString());
        response.addHeader("set-cookie", cookies.getAccessToken().toString());
        return new ResponseEntity<>(ResponseDto.res(true, "로그인 성공"), HttpStatus.OK);
    }


}
