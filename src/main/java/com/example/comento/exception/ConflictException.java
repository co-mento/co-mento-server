package com.example.comento.exception;

import com.example.comento.exception.errorcode.ErrorCode;

public class ConflictException extends CustomException{
    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}
