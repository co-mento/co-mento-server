package com.example.comento.domain.like;

import com.example.comento.domain.BaseEntity;
import com.example.comento.domain.problem.Problem;
import com.example.comento.domain.user.UserProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "problem_like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemLike extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_profile_id", nullable = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile userProfile;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    Problem problem;

}
