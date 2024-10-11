package com.example.comento.auth.constant;

public class RegularExpressionPatterns {

    public static final String APPLICATION_USERID_PATTERN = "^(?=.*[A-Za-z])(?=.*[\\d])[A-Za-z\\d]{6,15}$"; //영어, 숫자 포함 6~15자
    public static final String APPLICATION_PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*_\\-+='|\\\\(){}\\[\\]:;'\"<>,.?/]).{8,15}$"; //영어,숫자, 특수문자 포함 8~15자
    public static final String APPLICATION_USERNAME_PATTERN = "^[a-zA-Z가-힣]{2,8}$"; //영어나 한글로 2~8자
}
