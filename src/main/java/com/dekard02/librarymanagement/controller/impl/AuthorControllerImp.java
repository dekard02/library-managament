package com.dekard02.librarymanagement.controller.impl;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dekard02.librarymanagement.api.ResponseBody;
import com.dekard02.librarymanagement.controller.AuthorController;
import com.dekard02.librarymanagement.dto.AuthorDto;
import com.dekard02.librarymanagement.entity.Author;
import com.dekard02.librarymanagement.helper.ResponseBodyHelper;
import com.dekard02.librarymanagement.service.AuthorService;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
public class AuthorControllerImp implements AuthorController {

    public final AuthorService authorService;
    public final ResponseBodyHelper responseBodyHelper;

    @Override
    @GetMapping
    public ResponseEntity<ResponseBody> getAllAuthors(
            @QuerydslPredicate(root = Author.class) Predicate predicate,
            @PageableDefault(size = 10) Pageable pageable) {
        var response = authorService.getAllAuthorsResponse(predicate, pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseBody> createAuthor(@RequestBody @Valid AuthorDto authorDto) {
        var author = authorService.saveAuthor(authorDto);
        var response = responseBodyHelper.success("author", author);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> getAuthor(@PathVariable Long id) {
        var authorDto = authorService.getAuthor(id);
        var response = responseBodyHelper.success("author", authorDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> updateAuthor(@PathVariable Long id,
            @RequestBody @Valid AuthorDto authorDto) {
        var updatedAuthorDto = authorService.updateAuthor(id, authorDto);
        var response = responseBodyHelper.success("author", updatedAuthorDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

}
