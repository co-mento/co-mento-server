package com.example.comento.solution.dto.response;

import com.example.comento.global.util.TimeUtil;
import com.example.comento.solution.dao.SolutionDao;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SolutionResponse {

    private UUID id;
    private Long problemId;
    private String problemTitle;
    private String userName;

    private String language;
    private String time;
    private int memory;
    private boolean isCorrect;
    private String timeAgo;

    public static SolutionResponse from(SolutionDao solutionDao){
        return new SolutionResponse(
                solutionDao.getId(),
                solutionDao.getProblemId(),
                solutionDao.getProblemTitle(),
                solutionDao.getUserName(),
                solutionDao.getLanguage(),
                solutionDao.getTime(),
                solutionDao.getMemory(),
                solutionDao.getIsCorrect(),
                TimeUtil.changeTimeAgo(solutionDao.getCreatedAt()));
    }

}
