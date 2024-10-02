package com.example.comento.domain.level;

import com.example.comento.domain.BaseEntity;
import com.example.comento.domain.problem.Problem;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "problem_level")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProblemLevel extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    private Problem problem;

}
