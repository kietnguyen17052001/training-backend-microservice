package com.banvien.product.service.impl;

import com.banvien.product.entity.Product;
import com.banvien.product.exception.BadRequestException;
import com.banvien.product.exception.NotFoundException;
import com.banvien.product.repo.ProductRepo;
import com.banvien.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo repo;

    @Cacheable(value = "products")
    @Override
    public List<Product> getAll(String search, int page, int size, String sortBy) {
        Pageable pageable = (sortBy == null) ? PageRequest.of(page, size) : PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> products;
        if (search == null) {
            products = repo.findAll(pageable);
        } else {
            products = repo.findByNameContaining(search, pageable);
            if (products.isEmpty()) {
                throw new NotFoundException("Not found " + search);
            }
        }
        return products.getContent();
    }

    @Cacheable(value = "product", key = "#id")
    @Override
    public Product getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Not found " + id));
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public Product create(Product product) {
        try {
            return repo.save(product);
        } catch (Exception e) {
            throw new BadRequestException("Name " + product.getName() + " is already exists");
        }
    }

    @Caching(evict = {@CacheEvict(value = "product", key = "#product.id"), @CacheEvict(value = "products", allEntries = true)})
    @Override
    public Product update(Product product) {
        try {
            return repo.save(product);
        } catch (Exception e) {
            throw new BadRequestException("Name " + product.getName() + " is already exists");
        }
    }

    @Caching(evict = {@CacheEvict(value = "product", key = "#id"), @CacheEvict(value = "products", allEntries = true)})
    @Override
    public void delete(Long id) {
        if (getById(id) != null) {
            repo.deleteById(id);
        } else {
            throw new NotFoundException("Not found " + id);
        }
    }
}
