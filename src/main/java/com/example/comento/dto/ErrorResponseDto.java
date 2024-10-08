package com.example.comento.dto;

import com.example.comento.exception.CustomException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE) //무조건 정적 팩토리로 생성
public class ErrorResponseDto {
    private final String errorCode;
    private final String message;
    private final String detail;

    public static ErrorResponseDto res(final CustomException customException){
        String errorCode = customException.getErrorCode().getStatus();
        String message = customException.getErrorCode().getMessage();
        String detail = customException.getDetail();
        return new ErrorResponseDto(errorCode, message, detail);
    }

    public static ErrorResponseDto res(final String errorCode, final Exception e){
        return new ErrorResponseDto(errorCode, e.getMessage(), null);
    }
}
