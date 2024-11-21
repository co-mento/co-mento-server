package com.example.comento.solvedstatus.domain;

import com.example.comento.global.domain.UuidTypeBaseEntity;
import com.example.comento.problem.damain.Problem;
import com.example.comento.user.domain.UserProfile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "solved_status")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SolvedStatus extends UuidTypeBaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @ManyToOne(optional = false)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column(nullable = false)
    private Boolean flag;

    public SolvedStatus(UserProfile profile, Problem problem, Boolean flag){
        this.userProfile = profile;
        this.problem = problem;
        this.flag = flag;
    }

    public void setTrue(){
        this.flag = Boolean.TRUE;
    }

    public void setFalse(){
        this.flag = Boolean.FALSE;
    }

}
