package com.example.comento.exception;

import com.example.comento.exception.errorcode.ErrorCode;

public class UnauthorizedException extends CustomException{
    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
    public UnauthorizedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
