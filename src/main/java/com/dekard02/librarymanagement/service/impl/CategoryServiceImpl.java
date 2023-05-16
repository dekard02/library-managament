package com.dekard02.librarymanagement.service.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dekard02.librarymanagement.api.ResponseBody;
import com.dekard02.librarymanagement.dto.CategoryDto;
import com.dekard02.librarymanagement.dto.mapper.CategoryDtoMapper;
import com.dekard02.librarymanagement.entity.Category;
import com.dekard02.librarymanagement.helper.ResponseBodyHelper;
import com.dekard02.librarymanagement.repository.CategoryRepository;
import com.dekard02.librarymanagement.service.CategoryService;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;
    private final ResponseBodyHelper responseBodyHelper;

    @PersistenceContext
    private final EntityManager entityManager;

    private Category findCategoryOrThrow(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public ResponseBody getAllCategoriesResponse(Predicate predicate, Pageable pageable) {
        var page = categoryRepository.findAll(predicate, pageable);
        var categoryDtos = new ArrayList<CategoryDto>();
        page
                .stream()
                .forEach(category -> categoryDtos.add(categoryDtoMapper.categoryToCategoryDto(category)));
        return responseBodyHelper.page(page, "categories", categoryDtos);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        var category = findCategoryOrThrow(id);
        return categoryDtoMapper.categoryToCategoryDto(category);
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        var category = categoryDtoMapper.categoryDtoToCategory(categoryDto);
        category.setId(null);
        categoryRepository.save(category);
        return categoryDtoMapper.categoryToCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        var category = findCategoryOrThrow(id);
        BeanUtils.copyProperties(categoryDto, category, "id", "createdAt");
        categoryRepository.save(category);
        return categoryDtoMapper.categoryToCategoryDto(category);
    }

    @Override
    public void deleteCategory(Long id) {
        var category = findCategoryOrThrow(id);
        categoryRepository.delete(category);
    }

}
