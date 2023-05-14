package com.dekard02.librarymanagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(BookLoanRecordItemKey.class)
public class BookLoanRecordItem {
    @Id
    private Long bookLoanRecordId;

    @Id
    private Long bookId;

    private Integer quantity;

    @ManyToOne
    @MapsId
    private Book book;

    @ManyToOne
    @MapsId
    private BookLoanRecord bookLoanRecord;
}
