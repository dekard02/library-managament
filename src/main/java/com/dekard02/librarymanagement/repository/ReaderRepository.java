package com.dekard02.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.dekard02.librarymanagement.entity.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long>,
        QuerydslPredicateExecutor<Reader> {

}
