package com.example.comento.problem.dto.response;

import com.example.comento.like.domain.ProblemLike;
import com.example.comento.problem.damain.ProblemCategory;
import com.example.comento.problem.damain.ProblemCollection;
import com.example.comento.problem.damain.ProblemLevel;
import com.example.comento.solution.domain.Solution;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProblemResponse {
    private Long id;
    private String title;
    private String content;
    private String inputExplain;
    private String outputExplain;
    private String inputExample;
    private String outputExample;
    private int timeLimit;
    private int memoryLimit;
    private String source;
    private List<ProblemCollection> problemCollectionList;
    private List<ProblemCategory> problemCategoryList;
    private List<ProblemLevel> problemLevelList;
}