package com.dekard02.librarymanagement.service;

import org.springframework.data.domain.Pageable;

import com.dekard02.librarymanagement.api.ResponseBody;
import com.dekard02.librarymanagement.dto.AuthorDto;
import com.querydsl.core.types.Predicate;

public interface AuthorService {
    public ResponseBody getAllAuthorsResponse(Predicate predicate, Pageable pageable);

    public AuthorDto getAuthor(Long id);

    public AuthorDto saveAuthor(AuthorDto authorDto);

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto);

    public void deleteAuthor(Long id);

}
