package com.example.comento.exception;

import com.example.comento.exception.errorcode.ErrorCode;


public class DtoValidationException extends CustomException{
    public DtoValidationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DtoValidationException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
