package com.example.comento.solution.dto.response;

import com.example.comento.solution.dao.SolutionDao;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SolutionListResponse {

    private List<SolutionResponse> solutionList;

    public static SolutionListResponse from(Page<SolutionDao> solutionDaos){
        return new SolutionListResponse(solutionDaos.map(SolutionResponse::from).toList());
    }

}
