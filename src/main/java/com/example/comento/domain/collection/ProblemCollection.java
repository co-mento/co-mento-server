package com.example.comento.domain.collection;

import com.example.comento.domain.BaseEntity;
import com.example.comento.domain.problem.Problem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "problem_collection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemCollection extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;
}
