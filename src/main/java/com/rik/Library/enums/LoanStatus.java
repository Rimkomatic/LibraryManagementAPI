package com.rik.Library.enums;

public enum LoanStatus {

//    'BORROWED', 'RETURNED', 'OVERDUE'
    BORROWED(1),
    RETURNED(0),
    OVERDUE(-1);

    private final int value;

    LoanStatus(int value)
    {
        this.value = value;
    }

    public static LoanStatus fromValue(int value)
    {
        for(LoanStatus loan : values()) {
            if(loan.value == value ) return loan;
        }

        throw  new IllegalArgumentException("Invalid loan status: " + value);
    }

}
