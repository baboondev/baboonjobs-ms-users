package com.baboondev.baboonjobsmsusers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserExistsException extends Exception {
    public UserExistsException(String message) {
        super(message);
    }
}
