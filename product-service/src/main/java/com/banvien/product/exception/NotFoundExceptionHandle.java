package com.banvien.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class NotFoundExceptionHandle {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleFunc(NotFoundException notFoundException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                notFoundException.getMessage(),
                status,
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(apiException, status);
    }

}
