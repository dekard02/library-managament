package com.dekard02.librarymanagement.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dekard02.librarymanagement.api.ResponseBody;
import com.dekard02.librarymanagement.dto.AuthorDto;
import com.dekard02.librarymanagement.dto.mapper.AuthorDtoMapper;
import com.dekard02.librarymanagement.entity.Author;
import com.dekard02.librarymanagement.helper.ResponseBodyHelper;
import com.dekard02.librarymanagement.repository.AuthorRepository;
import com.dekard02.librarymanagement.service.AuthorService;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorDtoMapper authorDtoMapper;
    private final ResponseBodyHelper responseBodyHelper;

    private Author findAuthorOrThrow(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    @Override
    public AuthorDto saveAuthor(AuthorDto authorDto) {
        var author = authorDtoMapper.authorDtoToAuthor(authorDto);
        author.setId(null);
        authorRepository.save(author);
        return authorDtoMapper.authorToAuthorDto(author);
    }

    @Override
    public ResponseBody getAllAuthorsResponse(Predicate predicate, Pageable pageable) {
        var authorDtos = new ArrayList<AuthorDto>();
        var page = authorRepository
                .findAll(predicate, pageable);
        page
                .stream()
                .forEach(author -> authorDtos.add(authorDtoMapper.authorToAuthorDto(author)));
        return responseBodyHelper.page(page, "authors", authorDtos);
    }

    @Override
    public AuthorDto getAuthor(Long id) {
        var author = findAuthorOrThrow(id);
        return authorDtoMapper.authorToAuthorDto(author);
    }

    @Override
    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        var author = findAuthorOrThrow(id);
        BeanUtils.copyProperties(authorDto, author, "id");
        authorRepository.save(author);
        return authorDtoMapper.authorToAuthorDto(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        var author = findAuthorOrThrow(id);
        authorRepository.delete(author);
    }

}
