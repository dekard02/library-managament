package com.dekard02.librarymanagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookLoanRecordItemKey implements Serializable {
    @Column(name = "book_loan_record_id")
    private Long bookLoanRecordId;

    @Column(name = "book_id")
    private Long bookId;
}
