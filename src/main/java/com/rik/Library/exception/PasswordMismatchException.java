package com.rik.Library.exception;

import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends BaseException{
    public PasswordMismatchException(String msg)
    {
        super(msg, HttpStatus.UNAUTHORIZED);
    }
}
