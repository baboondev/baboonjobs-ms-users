package com.baboondev.baboonjobsmsusers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidRoleException extends Exception {
    public InvalidRoleException(String message) {
        super(message);
    }
}