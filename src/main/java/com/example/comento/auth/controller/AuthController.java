package com.example.comento.auth.controller;

import com.example.comento.auth.annotation.AuthenticationPrincipal;
import com.example.comento.auth.dto.request.LoginRequest;
import com.example.comento.auth.dto.request.SignUpRequest;
import com.example.comento.auth.dto.response.Principal;
import com.example.comento.auth.dto.response.TokenResponseCookies;
import com.example.comento.auth.service.AuthService;
import com.example.comento.auth.service.TokenService;
import com.example.comento.global.dto.ResponseDto;
import com.example.comento.user.domain.User;
import com.example.comento.user.dto.response.UserProfileResponse;
import com.example.comento.user.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;
    private final UserProfileService userProfileService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입 api", description = "userId, password, name을 입력하여 회원가입")
    public ResponseEntity<ResponseDto<Void>> signUp(@Valid @RequestBody SignUpRequest request){
        authService.signUp(request);
        return new ResponseEntity<>(ResponseDto.res(true, "회원가입 성공"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 api", description = "userId, password를 입력하여 로그인 함")
    public ResponseEntity<ResponseDto<UserProfileResponse>> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response){
        User user = authService.login(request);
        UUID id = user.getId();
        TokenResponseCookies cookies = tokenService.issueToken(id.toString());
        response.addHeader("set-cookie", cookies.getAccessToken().toString());
        UserProfileResponse userProfileResponse = userProfileService.getProfileResponse(user);
        return new ResponseEntity<>(ResponseDto.res(true, "로그인 성공", userProfileResponse), HttpStatus.OK);
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃 api", description = "토큰이 만료되도록 하여 로그아웃 됨")
    public ResponseEntity<ResponseDto<Void>> logout(@AuthenticationPrincipal Principal principal, HttpServletResponse response){
        TokenResponseCookies cookies = tokenService.issueExpiredToken();
        response.addHeader("set-cookie", cookies.getAccessToken().toString());
        return new ResponseEntity<>(ResponseDto.res(true, "로그아웃 성공"), HttpStatus.OK);
    }

    @GetMapping("/redirect/naver")
    @Operation(summary = "네이버 로그인 시 리다이렉트 uri", description = "프론트에서 요청하는 uri가 아님. 네이버 OAuth 사용시 자동으로 사용됨")
    public ResponseEntity<ResponseDto<UserProfileResponse>> naverSignUp(
            @RequestParam(name = "code") String authorizeCode,
            @RequestParam(name = "state") String state,
            HttpServletResponse response){
        User user = authService.naverOAuthLogin(authorizeCode, state);
        UUID id = user.getId();
        TokenResponseCookies cookies = tokenService.issueToken(id.toString());
        response.addHeader("set-cookie", cookies.getAccessToken().toString());
        UserProfileResponse userProfileResponse = userProfileService.getProfileResponse(user);
        return new ResponseEntity<>(ResponseDto.res(true, "Naver 로그인 성공", userProfileResponse), HttpStatus.OK);
    }

}
