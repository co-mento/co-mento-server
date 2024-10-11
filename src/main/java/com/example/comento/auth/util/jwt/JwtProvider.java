package com.example.comento.auth.util.jwt;

import com.example.comento.global.exception.UnauthorizedException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


public class JwtProvider {

    private final SecretKey key;
    private final long validityInMilliseconds;

    //상속 받는 토큰 provider만 생성자를 사용하도록 함.
    protected JwtProvider(String key, long validityInMilliseconds){
        this.key = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String createToken(final String payload){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setExpiration(expiration)
                .setSubject(payload)
                .setIssuedAt(now)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getPayload(final String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (JwtException e){
            throw new UnauthorizedException(ErrorCode.INVALID_TOKEN);
        }
    }

}
