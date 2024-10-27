package com.example.comento.problem.damain;

import com.example.comento.global.domain.UuidTypeBaseEntity;
import com.example.comento.level.domain.Level;
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
public class ProblemLevel extends UuidTypeBaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    private Problem problem;

}
