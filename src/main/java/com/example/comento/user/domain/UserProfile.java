package com.example.comento.user.domain;

import com.example.comento.global.domain.BaseEntity;
import com.example.comento.like.domain.ProblemLike;
import com.example.comento.solution.domain.Solution;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "user_profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserProfile extends BaseEntity{

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

}
