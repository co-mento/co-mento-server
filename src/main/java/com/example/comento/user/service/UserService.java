package com.example.comento.user.service;

import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.user.domain.User;
import com.example.comento.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpaRepository userJpaRepository;

    public User findById(UUID id){
        return userJpaRepository.findById(id).orElseThrow(()->
                new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    public User findByUserId(String  userId){
        return userJpaRepository.findByUserId(userId).orElseThrow(()->
                new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
