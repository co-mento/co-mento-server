package com.example.comento.problem.damain;

import com.example.comento.global.domain.LongTypeBaseEntity;
import com.example.comento.like.domain.ProblemLike;
import com.example.comento.solution.domain.Solution;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "problem")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Problem extends LongTypeBaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 1000, nullable = false)
    private String input_explain;

    @Column(length = 1000, nullable = false)
    private String output_explain;

    @Column(nullable = false)
    private String input_example;

    @Column(nullable = false)
    private String output_example;

    @Column(nullable = false)
    private int time_limit;

    @Column(nullable = false)
    private int memory_limit;

    @Column
    private String source;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Solution> solutionList;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProblemLike> problemLikes;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProblemCollection> problemCollectionList;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProblemCategory> problemCategoryList;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProblemLevel> problemLevelList;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TestCase> testCases;



}
