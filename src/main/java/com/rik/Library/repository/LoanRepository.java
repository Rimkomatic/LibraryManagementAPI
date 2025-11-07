package com.rik.Library.repository;

import com.rik.Library.entity.Loan;
import com.rik.Library.entity.Member;
import com.rik.Library.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan , Long> {

    @Query(value = "SELECT * FROM loans WHERE member_id = :member_id", nativeQuery = true)
    List<Loan> getOneMembersList(@Param("member_id") Long member_id);

    List<Loan> findByStatus(LoanStatus status);

    @Modifying
    @Query("UPDATE Loan l SET l.status = 'OVERDUE' WHERE l.status = 'BORROWED' AND l.loan_date <= :cutoffDate")
    int updateOverdueLoans(@Param("cutoffDate") LocalDate cutoffDate);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Loan l SET l.status = 'RETURNED', l.return_date = CURRENT_DATE WHERE l.id = :id")
    int updateLoanToReturned(@Param("id") long id);


    @Query("SELECT l from Loan l WHERE l.member=:member AND l.status=:status")
    List<Loan> statusCount(@Param("member") Member member , @Param("status") LoanStatus status);

}

