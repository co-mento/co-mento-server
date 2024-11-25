package com.example.comento.solution.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class GradingServerResponse {
    @JsonProperty("all_success")
    private boolean allSuccess;
    @JsonProperty("max_memory")
    private int maxMemory;
    @JsonProperty("max_time")
    private String maxTime;
    @JsonProperty("results")
    private List<Result> results;

    @Data
    public static class Result {
        private String token;
        private String status;
        private String time;
        private int memory;
    }
}

