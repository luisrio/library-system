package com.yourorg.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Fine {
    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    private BigDecimal amount;

    private boolean paid;
}
