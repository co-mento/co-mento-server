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

    //Unauthorized
    INVALID_TOKEN("4011", "유효하지 않은 토큰입니다."),
    TOKEN_NOT_FOUND("4012", "토큰이 존재하지 않습니다."),
    USER_UNAUTHORIZED("4013", "로그인에 실패했습니다."),


    //Not_Found
    USER_NOT_FOUND("4040", "존재하지 않는 유저입니다."),
    PAGE_NOT_FOUND("4041", "존재하지 않는 페이지입니다."),
    PROBLEM_NOT_FOUND("4042", "존재하지 않는 문제입니다."),
    SOLUTION_NOT_FOUND("4043", "존재하지 않는 솔루션입니다"),
    LEVEL_NOT_FOUND("4044", "존재하지 않는 레벨입니다."),
    CATEGORY_NOT_FOUND("4044", "존재하지 않는 카테고리입니다."),

    //Conflict
    LIKE_CONFLICT("4090", "불가한 좋아요 요청입니다."),
    INVALID_AI_REVIEW("4091", "AI 리뷰 요청이 불가한 솔루션입니다.")


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
