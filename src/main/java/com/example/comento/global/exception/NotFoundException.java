package com.example.comento.global.exception;

import com.example.comento.global.exception.errorcode.ErrorCode;

public class NotFoundException extends CustomException{
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
