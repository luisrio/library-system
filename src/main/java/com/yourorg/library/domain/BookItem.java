package com.yourorg.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookItem {
    @Id
    private String barcode;

    @Enumerated(EnumType.STRING)
    private BookStatus status = BookStatus.AVAILABLE;

    @ManyToOne
    private Rack rack;

    @ManyToOne
    private Book book;

    @OneToOne(mappedBy = "bookItem")
    private Loan loan;
}
