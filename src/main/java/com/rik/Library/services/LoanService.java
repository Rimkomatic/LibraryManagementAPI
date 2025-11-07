package com.rik.Library.services;

import com.rik.Library.controller.LoanController;
import com.rik.Library.controller.MemberController;
import com.rik.Library.dto.Loan.LoanCreationRequest;
import com.rik.Library.entity.Book;
import com.rik.Library.entity.Loan;
import com.rik.Library.entity.Member;
import com.rik.Library.enums.LoanStatus;
import com.rik.Library.repository.BookRepository;
import com.rik.Library.repository.LoanRepository;
import com.rik.Library.repository.MemberRepository;
import com.rik.Library.util.IdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository repository;

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    LoanService(LoanRepository loanRepository , MemberRepository memberRepository , BookRepository bookRepository)
    {
        this.repository = loanRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }


    public List<Loan> getAll()
    {
        return this.repository.findAll();
    }

    public Loan getOneWithId(long id)
    {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking Details not found"));
    }

    @Transactional
    public Loan addNew(LoanCreationRequest loan)
    {
        Loan newLoan = new Loan();

        long id;

        do{
            id = IdGenerator.generateSevenDigitId();
        } while (this.repository.existsById(id));

        newLoan.setId(id);

        Member member = memberRepository.findById(loan.member_id())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        newLoan.setMember(member);

        Book book = bookRepository.findById(loan.book_id())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if( this.repository.statusCount(member,LoanStatus.OVERDUE).size()>2 || this.repository.statusCount(member,LoanStatus.BORROWED).size()>5  )
        {
            throw new RuntimeException("Borrowing limit reached: Member already has 5 borrowed or 2 overdue books.");
        }

        if(book.getAvailable_copies()==0) {
            throw new RuntimeException("Not Enough Books available to borrow");
        }



        newLoan.setBook(book);

        this.bookRepository.decreseAvialableCopies(book.getId());

        return repository.save(newLoan);
    }

    @Transactional
    public Loan returnBook(long id)
    {
        Loan loan = this.repository.findById(id)
                .orElseThrow(()->new RuntimeException("Loan Not Found"));

        this.bookRepository.increaseAvialableCopies(loan.getBook().getId());
        int updated = this.repository.updateLoanToReturned(loan.getId());
        if (updated == 0) throw new RuntimeException("No matching loan found or already returned");


        return this.getOneWithId(loan.getId());
    }

    public List<Loan> countLoansByStatusForMember(long memberId, LoanStatus status) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return repository.statusCount(member, status);
    }


    public List<Loan> getLoansForMember(long id)
    {
        return this.repository.getOneMembersList(id);
    }

    public List<Loan> getLoansByStatus(LoanStatus status) {
        return repository.findByStatus(status);
    }

    @Transactional
    public int updateOverdueLoans() {
        LocalDate cutoffDate = LocalDate.now().minusDays(30);
        return repository.updateOverdueLoans(cutoffDate);
    }



}
