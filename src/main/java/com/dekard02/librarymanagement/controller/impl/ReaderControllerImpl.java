package com.dekard02.librarymanagement.controller.impl;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dekard02.librarymanagement.api.ResponseBody;
import com.dekard02.librarymanagement.controller.ReaderController;
import com.dekard02.librarymanagement.dto.ReaderDto;
import com.dekard02.librarymanagement.entity.Reader;
import com.dekard02.librarymanagement.helper.ResponseBodyHelper;
import com.dekard02.librarymanagement.service.ReaderService;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("readers")
@RequiredArgsConstructor
public class ReaderControllerImpl implements ReaderController {

    private final ResponseBodyHelper responseBodyHelper;
    private final ReaderService readerService;

    @Override
    @GetMapping
    public ResponseEntity<ResponseBody> getAllReaders(
            @QuerydslPredicate(root = Reader.class) Predicate predicate,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable) {
        var response = readerService.getAllReadersResponse(predicate, pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ResponseBody> getReader(@PathVariable Long id) {
        var reader = readerService.getReader(id);
        var response = responseBodyHelper.success("reader", reader);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseBody> createReader(
            @Valid ReaderDto.Request readerDto) {
        var reader = readerService.saveReader(readerDto);
        var response = responseBodyHelper.success("reader", reader);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<ResponseBody> updateReader(
            @PathVariable Long id,
            @Valid ReaderDto.Request readerDto) {
        var reader = readerService.updateReader(id, readerDto);
        var response = responseBodyHelper.success("reader", reader);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseBody> deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
        return ResponseEntity.noContent().build();
    }

}
