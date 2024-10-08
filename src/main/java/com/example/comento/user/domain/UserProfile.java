package com.example.comento.user.domain;

import com.example.comento.like.domain.ProblemLike;
import com.example.comento.solution.domain.Solution;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "user_profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class UserProfile {

    @Id
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private long experience;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public UserProfile (final User user, final String name){
        this.user = user;
        this.name = name;
        this.experience = 0;
    }

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Solution> solution;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProblemLike> problemLikes;

}
