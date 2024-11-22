package com.example.comento.level.service;


import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.level.domain.Level;
import com.example.comento.level.repository.LevelJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelJpaRepository levelJpaRepository;


    public Level find(Long id){
        return levelJpaRepository.findById(id).orElseThrow(()->
                new NotFoundException(ErrorCode.LEVEL_NOT_FOUND));
    }
}
