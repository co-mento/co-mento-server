package com.example.comento.category.service;

import com.example.comento.category.domain.Category;
import com.example.comento.category.dto.response.AllCategories;
import com.example.comento.category.repository.CategoryJpaRepository;
import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryJpaRepository categoryRepository;

    @Transactional
    public Category createCategory(String name) {
        Category category = Category.builder()
                .name(name)
                .build();
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Category getCategory(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public AllCategories getAllCategories() {
        return AllCategories.from(categoryRepository.findAll());
    }

    @Transactional
    public Category updateCategory(UUID categoryId, String newName) {
        Category category = getCategory(categoryId);
        category.setName(newName);  // Category 엔티티에 setName 메서드가 있다고 가정합니다.
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(UUID categoryId) {
        Category category = getCategory(categoryId);
        categoryRepository.delete(category);
    }

    public Category find(String name){
        return categoryRepository.findCategoryByName(name).orElseThrow(()->
                new NotFoundException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    public boolean isExistCategory(List<String> names){
        if(names.stream().anyMatch(name -> !categoryRepository.existsByName(name))){
            return false;
        }
        return true;
    }
}