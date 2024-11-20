package com.example.comento.problem.repository.problem;

import com.example.comento.problem.dto.response.ProblemDetailInformation;
import com.example.comento.problem.dto.response.ProblemPreview;
import com.example.comento.user.domain.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProblemCustomRepository {
    public Page<ProblemDetailInformation> getProblemPreviews(Pageable pageable,
                                                             UserProfile profile,
                                                             Long levelId,
                                                             UUID categoryId,
                                                             Boolean isSolved,
                                                             UUID collectionId,
                                                             String keyword);
}
