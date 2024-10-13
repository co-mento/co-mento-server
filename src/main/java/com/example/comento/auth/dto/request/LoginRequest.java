package com.example.comento.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import static com.example.comento.auth.constant.RegularExpressionPatterns.APPLICATION_PASSWORD_PATTERN;
import static com.example.comento.auth.constant.RegularExpressionPatterns.APPLICATION_USERID_PATTERN;

@Getter
public class LoginRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = APPLICATION_USERID_PATTERN, message = "아이디는 영문과 숫자를 포함해야 하며, 6자 이상, 15자 이하여야 합니다.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = APPLICATION_PASSWORD_PATTERN, message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 하며, 8자 이상, 15자 이하여야 합니다.")
    private String password;

}
