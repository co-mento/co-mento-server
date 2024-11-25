package com.example.comento.solution.domain;

import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Language {
    C("c", 50),
    C_PLUS_PLUS("c++", 54),
    C_SHARP("c#", 51),
    JAVA("java", 62),
    PYTHON("python", 71),
    KOTLIN("kotlin", 78),
    JAVA_SCRIPT("javascript", 63),
    R("r", 80),
    GO("go", 60),
    PHP("php", 68);

    private final String name;
    private final int id;

    public static Language find(String name){
         return Arrays.stream(Language.values())
                .filter(language -> name.equals(language.getName()))
                .findFirst()
                .orElseThrow(()->new NotFoundException(ErrorCode.LANGUAGE_NOT_FOUND));
    }
}
