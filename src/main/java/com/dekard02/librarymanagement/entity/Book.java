package com.dekard02.librarymanagement.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(length = 150, nullable = false)
    private String slug;

    @Column(length = 15, nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book")
    private Collection<BookPhoto> photos;

    @ManyToMany
    private Collection<Category> categories;

    @ManyToMany(mappedBy = "books")
    private Collection<BookLoanRecord> bookLoanRecords;
}
