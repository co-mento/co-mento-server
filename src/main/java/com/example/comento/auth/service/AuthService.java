package com.example.comento.auth.service;

import com.example.comento.auth.dto.request.SignUpRequest;
import com.example.comento.auth.util.password.PasswordHashEncryption;
import com.example.comento.user.domain.User;
import com.example.comento.user.domain.UserProfile;
import com.example.comento.user.repository.UserJpaRepository;
import com.example.comento.user.repository.UserProfileJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final PasswordHashEncryption passwordHashEncryption;
    private final UserJpaRepository userJpaRepository;
    private final UserProfileJpaRepository userProfileJpaRepository;


    @Transactional
    public void signUp(SignUpRequest request){
        String salt = issueSalt();
        String hashedPassword = passwordHashEncryption.encrypt(request.getPassword(), salt);
        User user = new User(request.getId(), hashedPassword, salt, request.getEmail(), request.getName());
        userJpaRepository.save(user);
    }

    /**
     *
     * @return SecureRandom으로 salt값을 생성해 반환
     */
    private String  issueSalt(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt); //Base64 인코딩 사용.
    }
}
