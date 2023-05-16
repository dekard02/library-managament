package com.dekard02.librarymanagement.controller.impl;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dekard02.librarymanagement.api.ResponseBody;
import com.dekard02.librarymanagement.controller.CategoryController;
import com.dekard02.librarymanagement.dto.CategoryDto;
import com.dekard02.librarymanagement.entity.Category;
import com.dekard02.librarymanagement.helper.ResponseBodyHelper;
import com.dekard02.librarymanagement.service.CategoryService;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;
    private final ResponseBodyHelper responseBodyHelper;

    @Override
    @GetMapping
    public ResponseEntity<ResponseBody> getAllCategories(
            @QuerydslPredicate(root = Category.class) Predicate predicate,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable) {
        var response = categoryService.getAllCategoriesResponse(predicate, pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ResponseBody> getCategory(@PathVariable Long id) {
        var category = categoryService.getCategory(id);
        var response = responseBodyHelper.success("category", category);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseBody> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        var category = categoryService.saveCategory(categoryDto);
        var response = responseBodyHelper.success("category", category);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<ResponseBody> updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid CategoryDto categoryDto) {
        var category = categoryService.updateCategory(id, categoryDto);
        var response = responseBodyHelper.success("category", category);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseBody> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
