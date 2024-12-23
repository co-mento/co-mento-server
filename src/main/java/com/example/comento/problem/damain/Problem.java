package com.example.comento.problem.damain;

import com.example.comento.global.domain.LongTypeBaseEntity;
import com.example.comento.level.domain.Level;
import com.example.comento.like.domain.ProblemLike;
import com.example.comento.solution.domain.Solution;
import com.example.comento.solvedstatus.domain.SolvedStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "problem")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Problem extends LongTypeBaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 1000)
    private String inputExplain;

    @Column(length = 1000, nullable = false)
    private String outputExplain;

    @Column
    private String inputExample;

    @Column(nullable = false)
    private String outputExample;

    @Column(nullable = false)
    private int timeLimit;

    @Column(nullable = false)
    private int memoryLimit;

    @Column
    private String source;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Solution> solutionList = new LinkedList<>();

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProblemLike> problemLikes = new LinkedList<>();

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProblemCollection> problemCollectionList = new ArrayList<>();

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProblemCategory> problemCategoryList = new LinkedList<>();

//    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<ProblemLevel> problemLevelList;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TestCase> testCases = new LinkedList<>();

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SolvedStatus> solvedStatuses = new LinkedList<>();


}
