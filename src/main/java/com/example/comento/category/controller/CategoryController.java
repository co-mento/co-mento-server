package com.example.comento.category.controller;

import com.example.comento.category.domain.Category;
import com.example.comento.category.dto.response.AllCategories;
import com.example.comento.category.service.CategoryService;
import com.example.comento.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/problems/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody String name) {
        Category category = categoryService.createCategory(name);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable UUID id) {
        Category category = categoryService.getCategory(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    @Operation(summary = "모든 카테고리 목록 조회")
    public ResponseEntity<ResponseDto<AllCategories>> getAllCategories() {
        AllCategories categories = categoryService.getAllCategories();
        return new ResponseEntity<>(ResponseDto.res(true, "카테고리 목록 조회 성공", categories), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestBody String newName) {
        Category updatedCategory = categoryService.updateCategory(id, newName);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}