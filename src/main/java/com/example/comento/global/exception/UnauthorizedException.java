package com.example.comento.global.exception;

import com.example.comento.global.exception.errorcode.ErrorCode;

public class UnauthorizedException extends CustomException{
    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
    public UnauthorizedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
