package com.example.comento.auth.service;

import com.example.comento.auth.dto.request.LoginRequest;
import com.example.comento.auth.dto.request.SignUpRequest;
import com.example.comento.auth.dto.response.NaverOAuthTokenResponse;
import com.example.comento.auth.dto.response.NaverUserProfile;
import com.example.comento.auth.dto.response.NaverUserResponse;
import com.example.comento.auth.util.jwt.AccessTokenProvider;
import com.example.comento.auth.util.password.PasswordHashEncryption;
import com.example.comento.global.exception.BadRequestException;
import com.example.comento.global.exception.UnauthorizedException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.global.util.CookieUtil;
import com.example.comento.user.domain.User;
import com.example.comento.user.domain.enums.Social;
import com.example.comento.user.repository.UserJpaRepository;
import com.example.comento.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserService userService;
    private final UserJpaRepository userJpaRepository;

    private final WebClient.Builder webClientBuilder;
    private final PasswordHashEncryption passwordHashEncryption;

    private final String naverTokenUrl = "https://nid.naver.com/oauth2.0/token";
    private final String naverOpenApiUri = "https://openapi.naver.com/v1/nid/me";
    private static String grantTypeAuthorization = "authorization_code";

    @Value("${oauth.naver.client-id}")
    private String naverClientId;
    @Value("${oauth.naver.client-secret}")
    private String naverClientSecret;


    @Transactional
    public void signUp(SignUpRequest request){
        String salt = issueSalt();
        String hashedPassword = passwordHashEncryption.encrypt(request.getPassword(), salt);
        User user = new User(request.getUserId(), hashedPassword, salt, request.getName(), Social.NONE);
        userJpaRepository.save(user);
    }

    public User login(LoginRequest request){
        User user = userService.findByUserId(request.getUserId());
        if (user.getSocial() != Social.NONE){
            throw new UnauthorizedException(ErrorCode.SOCIAL_USER_LOGIN_REQUEST);
        }
        if(passwordHashEncryption.matches(request.getPassword(), user.getSalt(), user.getPassword())){
            return user;
        }
        throw new UnauthorizedException(ErrorCode.USER_UNAUTHORIZED);
    }

    /**
     *
     * @param authorizeCode 네이버에서 보내오는 authorizeCode
     * @param state 네이버에서 보내오는 state
     * @return 기존에 유저가 존재하면 해당 유저 반환, 첫 로그인이라면 자동으로 회원가입 진행 후 반환.
     */
    @Transactional
    public User naverOAuthLogin(String authorizeCode, String state){

        NaverOAuthTokenResponse naverOAuthTokenResponse = getNaverAccessToken(authorizeCode, state);
        log.info(naverOAuthTokenResponse.toString());
        log.info(naverOAuthTokenResponse.getAccessToken());
        NaverUserProfile naverUserProfile = getNaverUserProfile(naverOAuthTokenResponse.getAccessToken());
        NaverUserResponse userResponse = naverUserProfile.getResponse();

        Optional<User> user = userJpaRepository.findByUserId(userResponse.getId());

        if(user.isPresent()){
            if(user.get().getSocial() == Social.NAVER){
                return user.get();
            }
            throw new UnauthorizedException(ErrorCode.SOCIAL_LOGIN_ERROR);
        }

        // 비밀번호는 이렇게 salt2개로 만든다. -> 소설 연동 유저는 사이트 로그인 자체가 불가능하지만 비밀번호 암호화도 진행함.
        String password = issueSalt();
        String salt = issueSalt();

        String nickname = URLDecoder.decode(userResponse.getNickname(), StandardCharsets.UTF_8);

        User newUser = new User(userResponse.getId(), password, salt, nickname, Social.NAVER);
        userJpaRepository.save(newUser);
        return newUser;
    }

    private NaverUserProfile getNaverUserProfile(String accessToken){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        return webClientBuilder
                .baseUrl(naverOpenApiUri).build()
                .get()
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToMono(NaverUserProfile.class)
                .block();
    }

    private NaverOAuthTokenResponse getNaverAccessToken(String authorizeCode, String state){
        String uriWithQueryString = UriComponentsBuilder.fromHttpUrl(naverTokenUrl)
                .queryParam("grant_type", grantTypeAuthorization)
                .queryParam("client_id", naverClientId)
                .queryParam("client_secret", naverClientSecret)
                .queryParam("code", authorizeCode)
                .queryParam("state", state)
                .toUriString();

        return webClientBuilder
                .baseUrl(naverTokenUrl).build()
                .get()
                .uri(uriWithQueryString)
                .retrieve()
                .bodyToMono(NaverOAuthTokenResponse.class)
                .block();
    }

    /**
     *
     * @return SecureRandom으로 salt값을 생성해 반환
     */
    private String issueSalt(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt); //Base64 인코딩 사용.
    }

}
