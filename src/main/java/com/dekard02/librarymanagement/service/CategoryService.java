package com.dekard02.librarymanagement.service;

import org.springframework.data.domain.Pageable;

import com.dekard02.librarymanagement.api.ResponseBody;
import com.dekard02.librarymanagement.dto.CategoryDto;
import com.querydsl.core.types.Predicate;

public interface CategoryService {
    public ResponseBody getAllCategoriesResponse(Predicate predicate, Pageable pageable);

    public CategoryDto getCategory(Long id);

    public CategoryDto saveCategory(CategoryDto categoryDto);

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto);

    public void deleteCategory(Long id);
}
