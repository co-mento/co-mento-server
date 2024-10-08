package com.example.comento.global.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ResponseDto<T> {
    private final boolean success;
    private final String message;
    private final T data;

    public static <T> ResponseDto<T> res(final boolean success, final String message){
        return new ResponseDto<>(success, message,null);
    }

    public static <T> ResponseDto<T> res (final boolean success, final String message, final T data){
        return new ResponseDto<>(success, message, data);
    }
}
