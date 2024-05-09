package com.iKeeper.homepage.domain.book.controller;

import com.iKeeper.homepage.domain.book.dto.BookReqeust;
import com.iKeeper.homepage.domain.book.entity.Book;
import com.iKeeper.homepage.domain.book.service.BookService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "")
    public ResponseEntity searchBookList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.BOOK_READ_ALL, bookService.searchBookList()), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity createBook(@RequestBody @Valid BookReqeust bookReqeust,
                                     BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            throw new CustomException("일부 입력된 값이 올바르지 않습니다.", ErrorCode.BOOK_INVALID_VALUE);
        }

        Book book = Book.createBook(bookReqeust);
        bookService.createBook(book);
        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.BOOK_POST), HttpStatus.CREATED);
    }
}
