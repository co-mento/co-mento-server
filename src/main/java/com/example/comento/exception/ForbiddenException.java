package com.example.comento.exception;

import com.example.comento.exception.errorcode.ErrorCode;

public class ForbiddenException extends CustomException{
    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
