package com.banvien.product.controller;

import com.banvien.product.entity.Product;
import com.banvien.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAllOrSearch(@RequestParam(name = "search", required = false) String search,
                                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                                        @RequestParam(name = "size", defaultValue = "3") int size,
                                                        @RequestParam(name = "sortBy", required = false) String sortBy) {
        return new ResponseEntity<>(service.getAll(search, (page > 0) ? page - 1 : page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(service.create(product), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return new ResponseEntity<>(service.update(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>("Delete successful", HttpStatus.OK);
    }
}
