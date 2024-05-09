package com.iKeeper.homepage.domain.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BookReqeust {

    private String name;

    private Boolean rental;

    private String borrower;

    private LocalDate rentalDay;

    public BookReqeust(String name, Boolean rental, String borrower,
                       LocalDate rentalDay) {

        this.name = name;
        this.rental = rental;
        this.borrower = borrower;
        this.rentalDay = rentalDay;
    }
}
