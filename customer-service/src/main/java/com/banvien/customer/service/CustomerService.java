package com.banvien.customer.service;

import com.banvien.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll(String search, int page, int size, String sortBy);

    Customer getById(Long id);

    Customer create(Customer product);

    Customer update(Customer product);

    void delete(Long id);
}
