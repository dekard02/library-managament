package com.dekard02.librarymanagement.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.dekard02.librarymanagement.dto.ReaderDto;
import com.dekard02.librarymanagement.entity.Reader;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReaderResponseDtoMapper {

    ReaderDto.Response readerToReaderDto(Reader reader);

    Reader readerDtoToReader(ReaderDto.Response readerDto);
}
