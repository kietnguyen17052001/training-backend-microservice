package com.banvien.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class);
    }

    @GetMapping("/")
    public String display() {
        return new String("customer-service");
    }
}
