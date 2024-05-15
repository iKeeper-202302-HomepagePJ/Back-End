package com.iKeeper.homepage.domain.book.service;

import com.iKeeper.homepage.domain.book.BookRequest;
import com.iKeeper.homepage.domain.book.dao.BookRepository;
import com.iKeeper.homepage.domain.book.entity.Book;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> searchBookList() {

        List<Book> searchBookList = bookRepository.findAll();
        return searchBookList;
    }

    public String createBook(Book book) {

        bookRepository.save(book);
        return "success";
    }

    @Transactional
    public String rentalBook(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new CustomException("해당 ID의 도서가 존재하지 않습니다.", ErrorCode.BOOK_INVALID_VALUE));

        book.updateRental(Boolean.TRUE, bookRequest.getBorrower(), bookRequest.getRentalDay());
        return "success";
    }

    @Transactional
    public String returnBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new CustomException("해당 ID의 도서가 존재하지 않습니다.", ErrorCode.BOOK_INVALID_VALUE));

        book.updateRental(Boolean.FALSE, null, null);
        return "success";
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
