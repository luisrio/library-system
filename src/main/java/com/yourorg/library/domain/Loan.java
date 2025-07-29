package com.yourorg.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "book_barcode")
    private BookItem bookItem;

    @ManyToOne
    private Member member;

    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
}
