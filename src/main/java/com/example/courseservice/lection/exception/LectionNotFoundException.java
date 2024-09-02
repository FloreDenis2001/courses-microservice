package com.example.courseservice.lection.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LectionNotFoundException extends RuntimeException{
    public LectionNotFoundException(String message) {
        super(message);
    }
}
