package com.dekard02.librarymanagement.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.dekard02.librarymanagement.dto.BookDto;
import com.dekard02.librarymanagement.entity.Book;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookDtoMapper {

    BookDto.Response bookToBookResDto(Book book);

    Book bookResDtoToBook(BookDto.Response bookResponseDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "photos", ignore = true)
    Book bookToBookCreateReqDto(BookDto.CreateRequest bookCreateRequestDto);
}
