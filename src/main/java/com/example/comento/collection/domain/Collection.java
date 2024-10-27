package com.example.comento.collection.domain;

import com.example.comento.global.domain.UuidTypeBaseEntity;
import com.example.comento.problem.damain.ProblemCollection;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity(name = "collection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Collection extends UuidTypeBaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private String description;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private List<ProblemCollection> problemCollectionList;

}
