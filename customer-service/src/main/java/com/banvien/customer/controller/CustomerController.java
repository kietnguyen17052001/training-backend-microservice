package com.banvien.customer.controller;

import com.banvien.customer.entity.Customer;
import com.banvien.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor

public class CustomerController {
    private final CustomerService service;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllOrSearch(@RequestParam(name = "search", required = false) String search,
                                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "3") int size,
                                                         @RequestParam(name = "sortBy", required = false) String sortBy) {
        return new ResponseEntity<>(service.getAll(search, (page > 0) ? page - 1 : page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        return new ResponseEntity<>(service.create(customer), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        return new ResponseEntity<>(service.update(customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>("Delete successful", HttpStatus.OK);
    }
}
