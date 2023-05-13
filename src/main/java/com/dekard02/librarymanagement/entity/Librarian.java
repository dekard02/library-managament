package com.dekard02.librarymanagement.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String fullName;

    @Column(length = 100, unique = true, nullable = false)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @ManyToMany
    private Collection<Role> roles;

    private LocalDateTime dateOfBirth;

    @OneToMany(mappedBy = "librarian")
    private Collection<BookLoanRecord> bookLoanRecords;

    @OneToMany(mappedBy = "librarian")
    private Collection<BookReturnRecord> bookReturnRecords;

}
