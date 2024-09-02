package com.example.courseservice.lection.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LectionAlreadyExists extends RuntimeException{
    public LectionAlreadyExists(String message) {
        super(message);
    }
}
