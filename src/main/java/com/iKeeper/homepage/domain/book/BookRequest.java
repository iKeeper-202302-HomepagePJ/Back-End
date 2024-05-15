package com.iKeeper.homepage.domain.book;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BookRequest {

    private String name;

    private String borrower;

    private LocalDate rentalDay;

    public BookRequest(String name, String borrower, LocalDate rentalDay) {

        this.name = name;
        this.borrower = borrower;
        this.rentalDay = rentalDay;
    }
}