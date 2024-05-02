package com.iKeeper.homepage.domain.book.dao;

import com.iKeeper.homepage.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
