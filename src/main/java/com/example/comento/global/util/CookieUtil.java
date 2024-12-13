package com.example.comento.global.util;

import com.example.comento.global.exception.UnauthorizedException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;

import java.time.Duration;

public class CookieUtil {
    private CookieUtil(){}
    public static ResponseCookie createTokenCookie(
            String tokenName, String tokenValue, Duration maxAge){
        return ResponseCookie.from(tokenName, tokenValue)
                .maxAge(maxAge)
                .path("/")
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .build();
    }

    public static ResponseCookie createTokenCookie(
            String tokenName, String tokenValue, Duration maxAge, String domain){
        return ResponseCookie.from(tokenName, tokenValue)
                .maxAge(maxAge)
                .path("/")
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .domain(domain)
                .build();
    }

    public static ResponseCookie createCookie(
            String tokenName, String tokenValue, Duration maxAge, String domain, boolean httpOnly){
        return ResponseCookie.from(tokenName, tokenValue)
                .maxAge(maxAge)
                .path("/")
                .httpOnly(httpOnly)
                .sameSite("None")
                .secure(true)
                .domain(domain)
                .build();
    }

    public static Cookie getCookie(final HttpServletRequest request, final String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        throw new UnauthorizedException(ErrorCode.TOKEN_NOT_FOUND);
    }

    public static boolean isExistCookie(final HttpServletRequest request, final String cookieName){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
