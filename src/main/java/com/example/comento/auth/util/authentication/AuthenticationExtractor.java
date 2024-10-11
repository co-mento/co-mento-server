package com.example.comento.auth.util.authentication;

import com.example.comento.auth.util.jwt.JwtEncoder;
import com.example.comento.auth.util.jwt.JwtProvider;
import com.example.comento.global.exception.UnauthorizedException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.global.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import static com.example.comento.auth.constant.TokenConstant.ACCESS_TOKEN;

public class AuthenticationExtractor {
    private AuthenticationExtractor(){}
    public static String extract(final HttpServletRequest request){
        Cookie cookie = CookieUtil.getCookie(request, ACCESS_TOKEN);
        return JwtEncoder.decodeJwtBearerToken(cookie.getValue());
    }
}
