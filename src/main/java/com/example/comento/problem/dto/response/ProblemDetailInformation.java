package com.example.comento.problem.dto.response;

import com.example.comento.problem.damain.Problem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ProblemDetailInformation {
    private Problem problem;
    private Boolean hasSolved;
}
