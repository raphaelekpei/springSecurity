package com.raphael.springsecurity.exception;

public class IncorrectEmailOrPasswordException extends RuntimeException {
    public IncorrectEmailOrPasswordException(String message) {
        super(message);
    }
}
