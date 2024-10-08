package com.example.comento.exception;

import com.example.comento.exception.errorcode.ErrorCode;

public class NotFoundException extends CustomException{
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
