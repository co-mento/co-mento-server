package com.example.comento.domain.problem;

import com.example.comento.domain.BaseEntity;
import com.example.comento.domain.category.ProblemCategory;
import com.example.comento.domain.collection.ProblemCollection;
import com.example.comento.domain.level.ProblemLevel;
import com.example.comento.domain.like.ProblemLike;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "problem")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Problem extends BaseEntity {

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
