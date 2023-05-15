package com.dekard02.librarymanagement.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.dekard02.librarymanagement.api.ResponseBody;
import com.dekard02.librarymanagement.dto.AuthorDto;
import com.querydsl.core.types.Predicate;

public interface AuthorController {
    public ResponseEntity<ResponseBody> getAllAuthors(Predicate predicate, Pageable pageable);

    public ResponseEntity<ResponseBody> getAuthor(Long id);

    public ResponseEntity<ResponseBody> createAuthor(AuthorDto authorDto);

    public ResponseEntity<ResponseBody> updateAuthor(Long id, AuthorDto authorDto);

    public ResponseEntity<ResponseBody> deleteAuthor(Long id);
}
