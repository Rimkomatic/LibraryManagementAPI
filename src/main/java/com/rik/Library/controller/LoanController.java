package com.rik.Library.controller;

import com.rik.Library.dto.Loan.LoanCreationRequest;
import com.rik.Library.entity.Loan;
import com.rik.Library.enums.LoanStatus;
import com.rik.Library.services.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/loan")
public class LoanController {

    private final LoanService loanService;

    LoanController(LoanService loanService)
    {
        this.loanService = loanService;
    }

    @GetMapping("/loans")
    public List<Loan> getAllLoans()
    {
        return this.loanService.getAll();
    }

    @PostMapping("/add")
    public Loan addLoan(@RequestBody LoanCreationRequest loan)
    {
        return this.loanService.addNew(loan);
    }

    @GetMapping("/{id}")
    public Loan getLoanDetails(@PathVariable long id)
    {
        return this.loanService.getOneWithId(id);
    }

    @GetMapping("/search/member/{id}")
    public List<Loan> getForMember(@PathVariable long id)
    {
        return this.loanService.getLoansForMember(id);
    }


    @GetMapping("/filter/{status}")
    public List<Loan> getLoansByStatus(@PathVariable("status") LoanStatus status) {
        return loanService.getLoansByStatus(status);
    }

    @GetMapping("/overdueUpdate")
    public int updateOverdueStatus()
    {
        return this.loanService.updateOverdueLoans();
    }

    @GetMapping("/return/{id}")
    public Loan returnBook(@PathVariable long id){
        return this.loanService.returnBook(id);
    }

    @GetMapping("/count/{id}/{status}")
    public int countStatusForMember(@PathVariable long id , @PathVariable LoanStatus status){
        return this.loanService.countLoansByStatusForMember(id,status).size();
    }

    @GetMapping("/filter/{id}/{status}")
    public List<Loan> filterStatusForMember(@PathVariable long id , @PathVariable LoanStatus status){
        return this.loanService.countLoansByStatusForMember(id,status);
    }

}
