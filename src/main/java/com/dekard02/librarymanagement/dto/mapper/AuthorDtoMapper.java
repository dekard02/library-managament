package com.dekard02.librarymanagement.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.dekard02.librarymanagement.dto.AuthorDto;
import com.dekard02.librarymanagement.entity.Author;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorDtoMapper {
    AuthorDto authorToAuthorDto(Author author);

    Author authorDtoToAuthor(AuthorDto authorDto);
}