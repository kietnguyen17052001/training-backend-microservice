package com.banvien.product.service;

import com.banvien.product.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll(String search);

    Product getById(Long id);

    Product create(Product product);

    Product update(Product product);

    void delete(Long id);
}
