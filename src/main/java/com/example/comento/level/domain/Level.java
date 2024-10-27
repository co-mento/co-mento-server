package com.example.comento.level.domain;

import com.example.comento.global.domain.UuidTypeBaseEntity;
import com.example.comento.problem.damain.ProblemLevel;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "level")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Level extends UuidTypeBaseEntity {

    @Column(nullable = false)
    private long experience;

    @OneToMany(mappedBy = "level", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<ProblemLevel> problemLevelList;
}
