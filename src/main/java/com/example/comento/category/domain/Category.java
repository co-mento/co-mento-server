package com.example.comento.category.domain;

import com.example.comento.global.domain.BaseEntity;
import com.example.comento.problem.damain.ProblemCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category",  cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<ProblemCategory> problemCategoryList;

}
