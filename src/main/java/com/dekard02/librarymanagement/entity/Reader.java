package com.dekard02.librarymanagement.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String fullName;

    private String address;

    @Column(length = 100, unique = true)
    private String email;

    private LocalDateTime dateOfBirth;

    @Column(length = 15, nullable = false)
    private String phone;

    @OneToMany(mappedBy = "reader")
    private Collection<BookLoanRecord> bookLoanRecords;
}
