package com.yourorg.library.web;

import com.yourorg.library.domain.Book;
import com.yourorg.library.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> all() {
        return bookRepository.findAll();
    }

    @GetMapping("/{isbn}")
    public Book get(@PathVariable String isbn) {
        return bookRepository.findById(isbn).orElseThrow();
    }
}
