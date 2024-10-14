package com.example.comento.user.service;

import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.user.dao.UserRankProfile;
import com.example.comento.user.domain.User;
import com.example.comento.user.dto.response.UserRankingResponse;
import com.example.comento.user.repository.UserJpaRepository;
import com.example.comento.user.repository.UserProfileJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final UserProfileJpaRepository profileJpaRepository;

    public User findById(UUID id){
        return userJpaRepository.findById(id).orElseThrow(()->
                new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    public User findByUserId(String userId){
        return userJpaRepository.findByUserId(userId).orElseThrow(()->
                new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    public UserRankingResponse getUserRanking(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<UserRankProfile> userProfilePage = profileJpaRepository.getUserRanking(pageable);
        UserRankingResponse userRankingResponse = UserRankingResponse.from(userProfilePage);

        return userRankingResponse;
    }

}
