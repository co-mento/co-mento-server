package com.example.comento.solution.domain;

import com.example.comento.global.domain.UuidTypeBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AiFeedback extends UuidTypeBaseEntity {

    @Column(length=2000)
    private String content;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "solution_id")
    private Solution solution;
}
