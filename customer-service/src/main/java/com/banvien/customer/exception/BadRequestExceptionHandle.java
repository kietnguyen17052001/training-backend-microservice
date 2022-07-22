package com.banvien.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class BadRequestExceptionHandle {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleFunc(BadRequestException badRequestException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                badRequestException.getMessage(),
                status,
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(apiException, status);
    }
}
