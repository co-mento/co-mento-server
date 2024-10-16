package com.example.comento.auth.intercepter;

import com.example.comento.auth.util.authentication.AuthenticationContext;
import com.example.comento.auth.util.authentication.AuthenticationExtractor;
import com.example.comento.auth.util.jwt.AccessTokenProvider;
import com.example.comento.auth.util.jwt.JwtProvider;
import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.global.util.UriMatcher;
import com.example.comento.user.domain.User;
import com.example.comento.user.repository.UserJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final AuthenticationContext authenticationContext;
    private final UserJpaRepository userJpaRepository;
    private final AccessTokenProvider accessTokenProvider;


    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {

        UriMatcher problemDetailUriMatcher = new UriMatcher(HttpMethod.GET, "/problems/{problem-id}");

        if(problemDetailUriMatcher.match(request)) return true;

        String accessToken = AuthenticationExtractor.extract(request);
        UUID id = UUID.fromString(accessTokenProvider.getPayload(accessToken));
        User user = findUserByUserId(id);
        authenticationContext.setPrincipal(user);
        return true;
    }

    private User findUserByUserId(UUID id){
        return userJpaRepository.findById(id).orElseThrow(()-> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
