package com.example.comento.global.exception.errorcode;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //BadRequest
    SIZE("4001", "길이가 유효하지 않습니다."),
    PATTERN("4001","형식에 맞지 않습니다."),
    NOT_BLANK("4002", "필수값이 공백입니다."),
    LENGTH("4003", "길이가 유효하지 않습니다."),
    EMAIL("4004", "이메일 형식이 유효하지 않습니다."),
    NOT_NULL("4005", "필수값이 공백입니다."),
    ;


    private final String status;
    private final String message;

    public static ErrorCode resolveValidationErrorCode(String code){
        return switch (code){
            case "Size" -> SIZE;
            case "Pattern" -> PATTERN;
            case "NotBlank" -> NOT_BLANK;
            case "Length" -> LENGTH;
            case "Email" -> EMAIL;
            case "NotNull" -> NOT_NULL;
            default -> throw new IllegalArgumentException("Unexpected value: "+ code);
        };
    }
}
