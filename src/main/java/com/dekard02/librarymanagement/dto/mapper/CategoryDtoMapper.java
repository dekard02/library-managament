package com.dekard02.librarymanagement.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.dekard02.librarymanagement.dto.CategoryDto;
import com.dekard02.librarymanagement.entity.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryDtoMapper {

    Category categoryDtoToCategory(CategoryDto categoryDto);

    CategoryDto categoryToCategoryDto(Category category);
}
