package com.banvien.product.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Data
public class ApiException {
    private final String msg;
    private final HttpStatus status;
    private final Timestamp timestamp;
}
