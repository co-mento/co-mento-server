package com.example.comento.auth.service;

import com.example.comento.auth.dto.response.TokenResponseCookies;
import com.example.comento.auth.util.jwt.AccessTokenProvider;
import com.example.comento.auth.util.jwt.JwtEncoder;
import com.example.comento.global.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import static com.example.comento.auth.constant.TokenConstant.ACCESS_TOKEN;
import static com.example.comento.auth.constant.TokenConstant.ACCESS_TOKEN_MAX_AGE;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final AccessTokenProvider accessTokenProvider;

    public TokenResponseCookies issueToken(String payload){
        ResponseCookie accessTokenCookie = createAccessTokenCookie(payload);
        return new TokenResponseCookies(accessTokenCookie);
    }

    private ResponseCookie createAccessTokenCookie(String payload){
        String accessToken = accessTokenProvider.createToken(payload);
        String bearerToken = JwtEncoder.encodeJwtToken(accessToken);
        return  CookieUtil.createTokenCookie(ACCESS_TOKEN, bearerToken, ACCESS_TOKEN_MAX_AGE);
    }

}