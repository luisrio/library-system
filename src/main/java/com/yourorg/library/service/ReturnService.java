package com.yourorg.library.service;

import com.yourorg.library.domain.BookItem;
import com.yourorg.library.domain.BookStatus;
import com.yourorg.library.domain.Loan;
import com.yourorg.library.domain.Member;
import com.yourorg.library.repository.BookItemRepository;
import com.yourorg.library.repository.LoanRepository;
import com.yourorg.library.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ReturnService {
    private final LoanRepository loanRepository;
    private final BookItemRepository bookItemRepository;
    private final MemberRepository memberRepository;

    public ReturnService(LoanRepository loanRepository,
                         BookItemRepository bookItemRepository,
                         MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.bookItemRepository = bookItemRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);

        BookItem item = loan.getBookItem();
        item.setStatus(BookStatus.AVAILABLE);
        item.setLoan(null);
        bookItemRepository.save(item);

        Member member = loan.getMember();
        member.decrementCheckout();
        memberRepository.save(member);
    }
}
