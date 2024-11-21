package com.example.comento.problem.dto.response;

import com.example.comento.problem.damain.Problem;
import com.example.comento.problem.damain.ProblemCategory;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.*;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProblemPreview {
    private Long problemId;
    private String title;
    private Boolean hasSolved;
    private Long level;
    private List<String> categories;

    public static ProblemPreview from(ProblemDetailInformation problemDetailInformation){
        Problem problem = problemDetailInformation.getProblem();
        List<ProblemCategory> problemCategoryList = problem.getProblemCategoryList();
        return new ProblemPreview(problem.getId(),
                problem.getTitle(),
                problemDetailInformation.getHasSolved(),
                problem.getLevel().getId(),
                problemCategoryList.stream()
                        .map(problemCategory -> problemCategory.getCategory().getName())
                        .toList());
    }

}
