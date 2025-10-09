package com.rik.Library.scheduler;

import com.rik.Library.services.LoanService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoanScheduler {

    private final LoanService loanService;

    public LoanScheduler(LoanService loanService) {
        this.loanService = loanService;
    }

    // Run every day at 2 AM
    @Transactional
    @Scheduled(cron = "0 0 2 * * ?")
    public void updateOverdueLoansDaily() {
        int updated = loanService.updateOverdueLoans();
        System.out.println("[" + java.time.LocalDateTime.now() + "] Updated overdue loans: " + updated);
    }
}
