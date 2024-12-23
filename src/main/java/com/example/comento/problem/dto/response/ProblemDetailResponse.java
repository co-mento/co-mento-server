package com.example.comento.problem.dto.response;

import com.example.comento.category.domain.Category;
import com.example.comento.problem.damain.Problem;
import com.example.comento.problem.damain.ProblemCategory;
import com.example.comento.problem.damain.ProblemCollection;
import com.example.comento.problem.damain.ProblemLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProblemDetailResponse {
    private Boolean hasSolved;
    private Boolean hasLiked;
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
    private Long level;
    private List<String> problemCategoryList;
    private Long numberOfProblemSolution;
    private Long numberOfCorrectUser;
    private Double roundedCorrectRate;



    public static ProblemDetailResponse from(ProblemDetailInformation problemDetailInformation,
                                             Boolean hasLiked,
                                             Long numberOfProblemSolution,
                                             Long numberOfCorrectUser,
                                             Double roundedCorrectRate){
        Problem problem = problemDetailInformation.getProblem();
        List<ProblemCategory> problemCategories = problem.getProblemCategoryList();
        return new ProblemDetailResponse(problemDetailInformation.getHasSolved(),
                hasLiked,
                problem.getId(),
                problem.getTitle(),
                problem.getContent(),
                problem.getInputExplain(),
                problem.getOutputExplain(),
                problem.getInputExample(),
                problem.getOutputExample(),
                problem.getTimeLimit(),
                problem.getMemoryLimit(),
                problem.getSource(),
                problem.getLevel().getId(),
                problemCategories.stream()
                        .map(problemCategory->problemCategory.getCategory().getName())
                        .toList(),
                numberOfProblemSolution,
                numberOfCorrectUser,
                roundedCorrectRate);
    }

}