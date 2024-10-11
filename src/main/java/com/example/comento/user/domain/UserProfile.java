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
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @Column(name = "user_id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId //User의 Pk를 UserProfile의 PK이자 Fk로 사용
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
