package com.example.comento.category.domain;

import com.example.comento.global.domain.UuidTypeBaseEntity;
import com.example.comento.problem.damain.ProblemCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "category")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends UuidTypeBaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category",  cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<ProblemCategory> problemCategoryList;

}