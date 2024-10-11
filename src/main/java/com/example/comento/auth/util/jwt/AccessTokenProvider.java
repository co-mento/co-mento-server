package com.example.comento.auth.util.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenProvider extends JwtProvider {
    public AccessTokenProvider(@Value("${security.jwt.access-token.secret-key}") String key,
                               @Value("${security.jwt.access-token.expire-length}") long validityInMilliseconds) {
        super(key, validityInMilliseconds);
    }
}
