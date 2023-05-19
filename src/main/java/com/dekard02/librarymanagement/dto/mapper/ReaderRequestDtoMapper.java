package com.dekard02.librarymanagement.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.dekard02.librarymanagement.dto.ReaderDto;
import com.dekard02.librarymanagement.entity.Reader;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReaderRequestDtoMapper {

    @Mapping(target = "photo", ignore = true)
    ReaderDto.Request readerToReaderDto(Reader reader);

    @Mapping(target = "photo", ignore = true)
    Reader readerDtoToReader(ReaderDto.Request readerDto);

}
