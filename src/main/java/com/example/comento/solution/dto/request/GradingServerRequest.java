package com.example.comento.solution.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Getter
@ToString
@NoArgsConstructor
@Builder
public class GradingServerRequest {
    @JsonProperty("source_code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String source_code;

    @JsonProperty("language_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer language_id;

    @JsonProperty("test_case")
    private String test_case;

    @JsonProperty("cpu_time_limit")
    private String cpu_time_limit;

    @JsonProperty("memory_limit")
    private String memory_limit;

}