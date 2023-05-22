package com.dekard02.librarymanagement.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.dekard02.librarymanagement.dto.BookPhotoDto;
import com.dekard02.librarymanagement.entity.BookPhoto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookPhotoMapper {

    BookPhoto bookPhotoDtoToBookPhoto(BookPhotoDto bookPhotoDto);

    BookPhotoDto bookPhotoToBookPhotoDto(BookPhoto bookPhoto);

}
