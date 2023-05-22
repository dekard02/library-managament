package com.dekard02.librarymanagement.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.dekard02.librarymanagement.dto.ReaderDto;
import com.dekard02.librarymanagement.entity.Reader;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReaderDtoMapper {

    ReaderDto.Response readerToReaderResDto(Reader reader);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photo", ignore = true)
    Reader readerReqDtoToReader(ReaderDto.Request readerDto);
}
