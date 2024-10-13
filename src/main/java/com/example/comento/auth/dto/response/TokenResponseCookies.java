package com.example.comento.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseCookie;

@Getter
@AllArgsConstructor
public class TokenResponseCookies {
    private ResponseCookie accessToken;
}
