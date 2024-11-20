package com.example.comento.category.dto.response;

import com.example.comento.category.domain.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CategoryResponse {
    private UUID id;
    private String name;

    public static CategoryResponse from(Category category){
        return new CategoryResponse(category.getId(), category.getName());
    }
}
