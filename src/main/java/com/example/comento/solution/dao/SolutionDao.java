package com.example.comento.solution.dao;

import java.time.LocalDateTime;
import java.util.UUID;

public interface SolutionDao {

    UUID getId();
    Long getProblemId();
    String getProblemTitle();

    String getUserName();

    int getMemory();
    String getTime();
    String getLanguage();
    boolean getIsCorrect();

    LocalDateTime getCreatedAt();

}
