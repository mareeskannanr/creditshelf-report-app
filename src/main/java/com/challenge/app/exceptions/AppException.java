package com.challenge.app.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
public class AppException extends RuntimeException {

    private final Object messages;
    private final HttpStatus statusCode;

    public AppException(List<String> messages, HttpStatus statusCode) {
        this.messages = messages;
        this.statusCode = statusCode;
    }

    public AppException(String message, HttpStatus statusCode) {
        this.messages = message;
        this.statusCode = statusCode;
    }

    public AppException(String message) {
        this.messages = message;
        this.statusCode = HttpStatus.BAD_REQUEST;
    }

}
