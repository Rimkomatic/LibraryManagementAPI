package com.rik.Library.entity;


import com.rik.Library.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate loan_date;


    private LocalDate return_date ;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('BORROWED', 'RETURNED', 'OVERDUE') DEFAULT 'BORROWED'")
    private LoanStatus status = LoanStatus.BORROWED;

    @PrePersist
    public void setLoanDate(){
        if(loan_date == null)
        {
            loan_date = LocalDate.now();
        }
    }

}
