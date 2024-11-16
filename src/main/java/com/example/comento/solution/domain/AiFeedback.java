package com.example.comento.solution.domain;

import com.example.comento.global.domain.UuidTypeBaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AiFeedback extends UuidTypeBaseEntity {

    @Column(length=2000)
    private String content;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "solution_id")
    private Solution solution;
}
