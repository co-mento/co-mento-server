package com.example.comento.category.dto.response;


import com.example.comento.category.domain.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AllCategories {
    List<CategoryResponse> categoryResponseList;

    public static AllCategories from(List<Category> categories){
        return new AllCategories(categories.stream()
                .map(CategoryResponse::from)
                .toList());
    }
}
