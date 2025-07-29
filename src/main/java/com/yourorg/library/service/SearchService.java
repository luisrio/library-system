package com.yourorg.library.service;

import com.yourorg.library.domain.Book;
import com.yourorg.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final BookRepository bookRepository;

    public SearchService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findAll(); // placeholder
    }
}
