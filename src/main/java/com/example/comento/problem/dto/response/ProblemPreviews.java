package com.example.comento.problem.dto.response;

import com.example.comento.global.dto.PaginationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProblemPreviews {
    private List<ProblemPreview> previewList;
    private PaginationResponse paginationResponse;

    public static ProblemPreviews from(Page<ProblemDetailInformation> problemDetailInformations){
        return new ProblemPreviews(problemDetailInformations
                .map(ProblemPreview::from)
                .toList(),
                PaginationResponse.from(problemDetailInformations));
    }
}
