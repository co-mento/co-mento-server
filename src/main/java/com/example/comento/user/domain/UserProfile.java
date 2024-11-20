package com.example.comento.user.domain;

import com.example.comento.global.domain.UuidTypeBaseEntity;
import com.example.comento.like.domain.ProblemLike;
import com.example.comento.solution.domain.Solution;
import com.example.comento.solvedstatus.domain.SolvedStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "user_profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserProfile extends UuidTypeBaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long experience;

    //같은 패키지 내 user에서만 생성 가능.
    protected UserProfile (final User user, final String name){
        this.user = user;
        this.name = name;
        this.experience = 0;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Solution> solution;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProblemLike> problemLikes;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SolvedStatus> solvedStatuses;

}
