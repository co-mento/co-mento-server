package com.example.comento.auth.intercepter;

import com.example.comento.auth.util.authentication.AuthenticationContext;
import com.example.comento.auth.util.authentication.AuthenticationExtractor;
import com.example.comento.auth.util.jwt.AccessTokenProvider;
import com.example.comento.auth.util.jwt.JwtProvider;
import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.global.util.CookieUtil;
import com.example.comento.global.util.UriMatcher;
import com.example.comento.user.domain.User;
import com.example.comento.user.domain.UserProfile;
import com.example.comento.user.repository.UserJpaRepository;
import com.example.comento.user.repository.UserProfileJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

import static com.example.comento.auth.constant.TokenConstant.ACCESS_TOKEN;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final AuthenticationContext authenticationContext;
    private final UserJpaRepository userJpaRepository;
    private final AccessTokenProvider accessTokenProvider;
    private final UserProfileJpaRepository userProfileJpaRepository;

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {

        if(CorsUtils.isPreFlightRequest(request)){
            return true;
        }

        UriMatcher problemDetailUriMatcher = new UriMatcher(HttpMethod.GET, "/problems/{problem-id}");
        UriMatcher userProfileDetailUriMatcher = new UriMatcher(HttpMethod.GET, "/users/{user-profile-id}");
        UriMatcher problemPreviewUriMatcher = new UriMatcher(HttpMethod.GET, "/problems");

        if(problemDetailUriMatcher.match(request) && !CookieUtil.isExistCookie(request, ACCESS_TOKEN)) {
            authenticationContext.setPrincipal(null, null);
            return true;
        }
        if(userProfileDetailUriMatcher.match(request)) return true;
        if(problemPreviewUriMatcher.match(request) && !CookieUtil.isExistCookie(request, ACCESS_TOKEN)) {
            authenticationContext.setPrincipal(null, null);
            return true;
        }

        String accessToken = AuthenticationExtractor.extract(request);
        UUID id = UUID.fromString(accessTokenProvider.getPayload(accessToken));
        User user = findUserByUserId(id);
        UserProfile profile = findUserProfileByUser(user);
        authenticationContext.setPrincipal(user,profile);
        return true;
    }

    private User findUserByUserId(UUID id){
        return userJpaRepository.findById(id).orElseThrow(()-> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    private UserProfile findUserProfileByUser(User user){
        return userProfileJpaRepository.findByUser(user).orElseThrow(()-> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
