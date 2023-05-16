package com.dekard02.librarymanagement.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.dekard02.librarymanagement.api.ResponseBody;
import com.dekard02.librarymanagement.dto.CategoryDto;
import com.querydsl.core.types.Predicate;

public interface CategoryController {
    public ResponseEntity<ResponseBody> getAllCategories(Predicate predicate, Pageable pageable);

    public ResponseEntity<ResponseBody> getCategory(Long id);

    public ResponseEntity<ResponseBody> createCategory(CategoryDto categoryDto);

    public ResponseEntity<ResponseBody> updateCategory(Long id, CategoryDto categoryDto);

    public ResponseEntity<ResponseBody> deleteCategory(Long id);
}
