package com.banvien.customer.service.impl;

import com.banvien.customer.entity.Customer;
import com.banvien.customer.exception.BadRequestException;
import com.banvien.customer.exception.NotFoundException;
import com.banvien.customer.repository.CustomerRepo;
import com.banvien.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo repo;

    @Cacheable(value = "customers")
    @Override
    public List<Customer> getAll(String search) {
        if (search == null) {
            return repo.findAll();
        } else {
            List<Customer> products = repo.findAll().stream()
                    .filter(product -> product.getName().contains(search))
                    .collect(Collectors.toList());
            if (!products.isEmpty()) {
                return products;
            } else {
                throw new NotFoundException("Not found " + search);
            }
        }
    }

    @Cacheable(value = "customer", key = "#id", unless = "#result == null")
    @Override
    public Customer getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Not found " + id));
    }

    @CacheEvict(value = "customers", allEntries = true)
    @Override
    public Customer create(Customer customer) {
        try {
            return repo.save(customer);
        } catch (Exception e) {
            throw new BadRequestException("Name " + customer.getName() + " is already exists");
        }
    }

    @Caching(evict = {@CacheEvict(value = "customer", key = "#customer.id"), @CacheEvict(value = "customers", allEntries = true)})
    @Override
    public Customer update(Customer customer) {
        try {
            return repo.save(customer);
        } catch (Exception e) {
            throw new BadRequestException("Name " + customer.getName() + " is already exists");
        }
    }

    @Caching(evict = {@CacheEvict(value = "customer", key = "#id"), @CacheEvict(value = "customers", allEntries = true)})
    @Override
    public void delete(Long id) {
        if (getById(id) != null) {
            repo.deleteById(id);
        } else {
            throw new NotFoundException("Not found " + id);
        }
    }
}
