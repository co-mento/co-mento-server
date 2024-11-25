package com.example.comento.solution.dto.response;

import com.example.comento.global.util.TimeUtil;
import com.example.comento.solution.domain.Solution;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SolutionDetailResponse {
    private UUID id;
    private Long problemId;
    private String problemTitle;
    private String userName;

    private String code;
    private String aiFeedback;

    private String language;
    private String errorReason;
    private String time;
    private int memory;
    private boolean isCorrect;
    private String timeAgo;

    public static SolutionDetailResponse from(Solution solution){
        return new SolutionDetailResponse(
                solution.getId(),
                solution.getProblem().getId(),
                solution.getProblem().getTitle(),
                solution.getUserProfile().getName(),
                solution.getCode(),
                solution.getAiFeedback() != null ? solution.getAiFeedback().getContent() : null,
                solution.getLanguage(),
                solution.getErrorReason(),
                solution.getTime(),
                solution.getMemory(),
                solution.isCorrect(),
                TimeUtil.changeTimeAgo(solution.getCreatedAt()));
    }
}
