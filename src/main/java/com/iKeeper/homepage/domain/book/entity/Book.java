package com.iKeeper.homepage.domain.book.entity;

import com.iKeeper.homepage.domain.book.dto.BookReqeust;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "library")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "biio_name")
    private String name;

    @Column(name = "book_rental")
    private Boolean rental;

    @Column(name = "book_borrower")
    private String borrower;

    @Column(name = "book_rental_day")
    private LocalDate rentalDay;

    @Builder
    public Book(String name, Boolean rental, String borrower, LocalDate rentalDay) {

        this.name = name;
        this.rental = rental;
        this.borrower = borrower;
        this.rentalDay = rentalDay;
    }

    public static Book createBook(BookReqeust bookReqeust) {

        Book book = Book.builder()
                .name(bookReqeust.getName())
                .rental(Boolean.FALSE)
                .build();
        return book;
    }
}