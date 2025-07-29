package com.yourorg.library.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int totalBooksCheckedOut;

    public void incrementCheckout() {
        totalBooksCheckedOut++;
    }

    public void decrementCheckout() {
        if (totalBooksCheckedOut > 0) {
            totalBooksCheckedOut--;
        }
    }
}
