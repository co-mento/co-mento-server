package com.example.comento.global.exception;

import com.example.comento.global.exception.errorcode.ErrorCode;
import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String detail;

    protected CustomException(ErrorCode errorCode){
      this.errorCode = errorCode;
      this.detail = null;
    }

    protected CustomException(ErrorCode errorCode, String detail){
        this.errorCode = errorCode;
        this.detail = detail;
    }

}
