package com.example.comento.global.exception;

import com.example.comento.global.exception.errorcode.ErrorCode;

public class ConflictException extends CustomException{
    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}