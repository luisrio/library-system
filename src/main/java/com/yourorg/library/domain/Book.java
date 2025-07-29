package com.yourorg.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    private String isbn;

    private String title;

    private LocalDate publicationDate;

    @ManyToMany
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<BookItem> items = new HashSet<>();

    public void addAuthor(Author author) {
        authors.add(author);
    }
}
