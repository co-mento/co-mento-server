package com.example.comento.global.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaginationResponse {
    private int currentPage;
    private int totalPage;

    public static <T> PaginationResponse from(Page<T> page){
        return new PaginationResponse(page.getNumber(), page.getTotalPages());
    }

}
