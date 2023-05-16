package com.dekard02.librarymanagement.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.dekard02.librarymanagement.entity.listener.CategoryListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({ CategoryListener.class, AuditingEntityListener.class })
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false, unique = true)
    private String name;

    @Column(length = 150, nullable = false)
    private String slug;

    private Boolean isDeleted;

    @ManyToMany(mappedBy = "categories")
    private Collection<Book> books;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
