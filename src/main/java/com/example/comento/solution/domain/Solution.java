package com.example.comento.solution.domain;

import com.example.comento.global.domain.UuidTypeBaseEntity;
import com.example.comento.problem.damain.Problem;
import com.example.comento.user.domain.UserProfile;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "solution")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Solution extends UuidTypeBaseEntity {

    @Column(nullable = false)
    private String language;

    @Column(length=2000)
    private String code;

    @Column(nullable = false)
    private boolean isCorrect;

    @Column(nullable = false)
    private int memory;

    @Column(nullable = false)
    private String time;

    @Column(nullable = true)
    private String errorReason;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_profile_id", nullable = false)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @OneToOne(mappedBy = "solution", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private AiFeedback aiFeedback;

    public void registerAiFeedBack(AiFeedback aiFeedback){
        if(this.aiFeedback == null){
            this.aiFeedback = aiFeedback;
        }
    }
}
