package com.example.comento.global.exception;

import com.example.comento.global.exception.errorcode.ErrorCode;

public class InternetException extends CustomException{
    public InternetException(ErrorCode errorCode) {
        super(errorCode);
    }
}
