package com.rik.Library.dto.Loan;

public record LoanCreationRequest(
        long book_id,
        long member_id
) {
}
