package com.rik.Library.exception;

import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends BaseException{
    public EmailNotFoundException(String msg)
    {
        super(msg, HttpStatus.NOT_FOUND);
    }
}
