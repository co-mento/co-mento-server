package com.example.comento.auth.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import static com.example.comento.auth.constant.RegularExpressionPatterns.*;

@Getter
public class SignUpRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = APPLICATION_USERID_PATTERN, message = "아이디는 영문과 숫자를 포함해야 하며, 6자 이상, 15자 이하여야 합니다.")
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = APPLICATION_PASSWORD_PATTERN, message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 하며, 8자 이상, 15자 이하여야 합니다.")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = APPLICATION_USERNAME_PATTERN, message = "닉네임은 영어나 한글로 작성하며 2자 이상, 8자 이하여야 합니다.")
    private String name;

}
