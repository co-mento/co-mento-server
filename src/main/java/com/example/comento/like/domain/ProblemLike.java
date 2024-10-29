package com.example.comento.like.domain;

import com.example.comento.global.domain.UuidTypeBaseEntity;
import com.example.comento.problem.damain.Problem;
import com.example.comento.user.domain.UserProfile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "problem_like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "problem_like", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_profile_id", "problem_id"}) // 복합 키 제약 조건
})
public class ProblemLike extends UuidTypeBaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", nullable = false)
//    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile userProfile;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    Problem problem;

}
