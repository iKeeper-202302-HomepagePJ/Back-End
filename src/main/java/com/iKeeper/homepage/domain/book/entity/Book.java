package com.iKeeper.homepage.domain.book.entity;

import com.iKeeper.homepage.domain.book.dto.BookReqeust;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_rental")
    private Boolean rental;

    @Column(name = "book_borrower")
    private String borrower;

    @Column(name = "book_rental_day")
    private LocalDate rentalDay;

    public void updateRental(Boolean rental, String borrower, LocalDate rentalDay) {

        this.rental = rental;
        this.borrower = borrower;
        this.rentalDay = rentalDay;
    }

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
                .borrower(null)
                .rentalDay(null)
                .build();
        return book;
    }
}