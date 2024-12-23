package com.example.comento.collection.controller;

import com.example.comento.auth.annotation.AuthenticationPrincipal;
import com.example.comento.auth.dto.response.Principal;
import com.example.comento.category.dto.response.AllCategories;
import com.example.comento.collection.dto.response.AllCollections;
import com.example.comento.collection.dto.response.CollectionProgresses;
import com.example.comento.collection.service.CollectionService;
import com.example.comento.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/problems/collections")
public class CollectionController {
    private final CollectionService collectionService;

    @GetMapping
    @Operation(summary = "모든 문제집 목록 조회")
    public ResponseEntity<ResponseDto<AllCollections>> getAllCollections() {
        AllCollections allCollections = collectionService.getAllCollection();
        return new ResponseEntity<>(ResponseDto.res(true, "문제집 목록 조회 성공", allCollections), HttpStatus.OK);
    }

    @GetMapping("/progress")
    @Operation(summary = "문제집 진행도 조회")
    public ResponseEntity<ResponseDto<CollectionProgresses>> getAllCollectionProgress(@AuthenticationPrincipal Principal principal){
        CollectionProgresses progresses = collectionService.getAllCollectionProgresses(principal);
        return new ResponseEntity<>(ResponseDto.res(true, "문제집 진행도 조회 성공", progresses), HttpStatus.OK);
    }

}
