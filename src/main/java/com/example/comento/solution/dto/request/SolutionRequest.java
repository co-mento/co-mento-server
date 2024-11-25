package com.example.comento.solution.dto.request;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class SolutionRequest {
    @NotBlank(message = "언어는 선택은 필수입니다.")
    private String language;
    @Length(max = 2000, message = "제출 가능 코드 길이는 2000자입니다.")
    private String code;
}
