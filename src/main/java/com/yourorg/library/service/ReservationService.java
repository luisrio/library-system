package com.yourorg.library.service;

import com.yourorg.library.domain.Book;
import com.yourorg.library.domain.Member;
import com.yourorg.library.domain.Reservation;
import com.yourorg.library.domain.ReservationStatus;
import com.yourorg.library.repository.BookRepository;
import com.yourorg.library.repository.MemberRepository;
import com.yourorg.library.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReservationService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(BookRepository bookRepository,
                              MemberRepository memberRepository,
                              ReservationRepository reservationRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public Reservation reserveBook(String memberId, String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow();
        Member member = memberRepository.findById(UUID.fromString(memberId)).orElseThrow();
        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setMember(member);
        reservation.setCreatedOn(LocalDateTime.now());
        reservation.setStatus(ReservationStatus.WAITING);
        return reservationRepository.save(reservation);
    }
}
