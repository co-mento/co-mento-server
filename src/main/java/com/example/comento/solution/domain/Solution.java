package com.example.comento.solution.domain;

import com.example.comento.global.domain.BaseEntity;
import com.example.comento.problem.damain.Problem;
import com.example.comento.user.domain.UserProfile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "solution")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Solution extends BaseEntity {

    @Column(nullable = false)
    private String language;

    @Column(length=2000)
    private String code;

    @Column(nullable = false)
    private boolean isCorrect;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_profile_id", nullable = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile userProfile;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

}
