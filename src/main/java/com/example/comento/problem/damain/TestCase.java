package com.example.comento.problem.damain;

import com.example.comento.global.domain.BaseEntity;
import com.example.comento.problem.damain.Problem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "test_case")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TestCase extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column(nullable = false)
    private String input;

    @Column(nullable = false, length = 1000)
    private String output;
}
