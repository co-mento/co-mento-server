package com.example.comento.problem.damain;

import com.example.comento.category.domain.Category;
import com.example.comento.global.domain.UuidTypeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "problem_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProblemCategory extends UuidTypeBaseEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

}
