package com.dekard02.librarymanagement.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.dekard02.librarymanagement.api.ResponseBody;
import com.dekard02.librarymanagement.dto.ReaderDto;
import com.querydsl.core.types.Predicate;

public interface ReaderController {

    public ResponseEntity<ResponseBody> getAllReaders(Predicate predicate, Pageable pageable);

    public ResponseEntity<ResponseBody> getReader(Long id);

    public ResponseEntity<ResponseBody> createReader(ReaderDto.Request readerDto);

    public ResponseEntity<ResponseBody> updateReader(Long id, ReaderDto.Request readerDto);

    public ResponseEntity<ResponseBody> deleteReader(Long id);
}