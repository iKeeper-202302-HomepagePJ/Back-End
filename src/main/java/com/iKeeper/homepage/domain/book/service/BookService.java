package com.iKeeper.homepage.domain.book.service;

import com.iKeeper.homepage.domain.book.dao.BookRepository;
import com.iKeeper.homepage.domain.book.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> searchBookList() {

        List<Book> searchBookList = bookRepository.findAll();
        return searchBookList;
    }

    public String createBook(Book library) {

        bookRepository.save(library);
        return "success";
    }
}
