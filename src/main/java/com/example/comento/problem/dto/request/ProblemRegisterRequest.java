package com.example.comento.problem.dto.request;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.util.List;


@Getter
public class ProblemRegisterRequest {
    @NotBlank
    private String title;

    @NotBlank
    @Length(max = 2000)
    private String content;

    @Length(max = 1000)
    private String inputExplain;

    @Length(max = 1000)
    @NotBlank
    private String outputExplain;

    private String inputExample;

    @NotBlank
    private String outputExample;

    @NotBlank
    private int timeLimit;

    @NotBlank
    private int memoryLimit;

    private String source;

    @Min(1) @Max(3)
    @NotBlank
    private Long level;

    @NotEmpty
    private List<TestCaseRequired> testcases;

    private List<String> categoryNames;

    public record TestCaseRequired(String input, String output){}
}
