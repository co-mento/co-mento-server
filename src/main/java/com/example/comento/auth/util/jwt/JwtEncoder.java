package com.example.comento.auth.util.jwt;

import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class JwtEncoder {
    public static final String TOKEN_TYPE = "Bearer ";

    private JwtEncoder(){}

    public static String encodeJwtToken(String token){
        String cookieValue = TOKEN_TYPE+token;
        return URLEncoder.encode(cookieValue, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
    }

    public static String decodeJwtBearerToken(String cookieValue){
        return URLDecoder.decode(cookieValue, StandardCharsets.UTF_8).substring(7);
    }
}
