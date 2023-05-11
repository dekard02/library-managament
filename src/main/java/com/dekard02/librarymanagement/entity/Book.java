package com.dekard02.librarymanagement.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String slug;

    private String isbn;

    private String description;

    @OneToOne
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<BookPhoto> photos;

    @ManyToMany
    private List<Category> categories;
}
