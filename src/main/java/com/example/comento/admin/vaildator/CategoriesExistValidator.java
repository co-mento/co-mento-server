package com.example.comento.admin.vaildator;

import com.example.comento.admin.annotation.ExistCategories;
import com.example.comento.category.service.CategoryService;
import com.example.comento.problem.dto.request.ProblemRegisterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, ProblemRegisterRequest> {
    private final CategoryService categoryService;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ProblemRegisterRequest problemRegisterRequest, ConstraintValidatorContext constraintValidatorContext) {
        List<String> categoryNames = problemRegisterRequest.getCategoryNames();
        categoryService.isExistCategory(categoryNames);
        return true;
    }

}
