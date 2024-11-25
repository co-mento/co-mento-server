package com.example.comento.problem.dto.request;

import com.example.comento.problem.damain.TestCase;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class TestCaseRequest {
    @ToString.Include
    private String stdin;
    @ToString.Include
    @JsonProperty("expected_output")
    private String expected_output;

    public static TestCaseRequest from(TestCase testCase){
        return new TestCaseRequest(testCase.getInput(), testCase.getOutput());
    }
}
