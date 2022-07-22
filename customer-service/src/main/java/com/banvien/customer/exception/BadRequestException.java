package com.banvien.customer.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String msg) {
        super(msg);
    }
}
