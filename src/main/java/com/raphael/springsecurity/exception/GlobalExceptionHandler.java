package com.raphael.springsecurity.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public UserNotFoundException UserNotFoundExceptionHandler(){
        return null;
    }

    @ExceptionHandler(value = IncorrectEmailOrPasswordException.class)
    public IncorrectEmailOrPasswordException IncorrectEmailOrPasswordExceptionHandler(){
        return null;
    }

}
