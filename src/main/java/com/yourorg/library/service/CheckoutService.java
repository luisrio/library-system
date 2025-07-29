package com.yourorg.library.service;

import com.yourorg.library.domain.*;
import com.yourorg.library.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CheckoutService {
    private static final int MAX_ACTIVE_LOANS = 5;
    private static final int MAX_LOAN_DAYS = 10;

    private final MemberRepository memberRepository;
    private final BookItemRepository bookItemRepository;
    private final LoanRepository loanRepository;

    public CheckoutService(MemberRepository memberRepository,
                           BookItemRepository bookItemRepository,
                           LoanRepository loanRepository) {
        this.memberRepository = memberRepository;
        this.bookItemRepository = bookItemRepository;
        this.loanRepository = loanRepository;
    }

    @Transactional
    public Loan checkoutBook(String memberId, String barcode) {
        Member member = memberRepository.findById(UUID.fromString(memberId))
                .orElseThrow();
        if (member.getTotalBooksCheckedOut() >= MAX_ACTIVE_LOANS) {
            throw new IllegalStateException("Checkout quota exceeded");
        }
        BookItem item = bookItemRepository.findById(barcode).orElseThrow();
        if (item.getStatus() != BookStatus.AVAILABLE) {
            throw new IllegalStateException("Book item not available");
        }
        Loan loan = new Loan();
        loan.setBookItem(item);
        loan.setMember(member);
        loan.setCheckoutDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(MAX_LOAN_DAYS));
        loanRepository.save(loan);

        item.setStatus(BookStatus.LOANED);
        item.setLoan(loan);
        bookItemRepository.save(item);

        member.incrementCheckout();
        memberRepository.save(member);
        return loan;
    }
}
