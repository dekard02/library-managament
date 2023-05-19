package com.dekard02.librarymanagement.service;

import org.springframework.data.domain.Pageable;

import com.dekard02.librarymanagement.api.ResponseBody;
import com.dekard02.librarymanagement.dto.ReaderDto;
import com.querydsl.core.types.Predicate;

public interface ReaderService {

    public static final String READER_PHOTO_DIR = "readers";

    public ResponseBody getAllReadersResponse(Predicate predicate, Pageable pageable);

    public ReaderDto.Response getReader(Long id);

    public ReaderDto.Response saveReader(ReaderDto.Request readerDto);

    public ReaderDto.Response updateReader(Long id, ReaderDto.Request readerDto);

    public void deleteReader(Long id);
}
