package com.example.comento.solution.dto.response;

import com.example.comento.solution.dao.ProblemId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ProblemIdsResponse {
    private final List<Long> problemIds;

    public static ProblemIdsResponse from(List<ProblemId> problemIds){
        return new ProblemIdsResponse(problemIds.stream().map(ProblemId::getId).collect(Collectors.toList()));
    }
}
