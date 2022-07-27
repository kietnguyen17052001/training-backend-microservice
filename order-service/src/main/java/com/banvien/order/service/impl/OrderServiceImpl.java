package com.banvien.order.service.impl;

import com.banvien.order.entity.Order;
import com.banvien.order.exception.NotFoundException;
import com.banvien.order.repository.OrderRepository;
import com.banvien.order.service.OrderService;
import com.banvien.order.vo.Customer;
import com.banvien.order.vo.Product;
import com.banvien.order.vo.ResponseTemplateVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repo;
    private final RestTemplate restTemplate;
    private static final String URL_GET_CUSTOMER = "http://localhost:6969/api/v1/customers/";
    private static final String URL_GET_PRODUCT = "http://localhost:6969/api/v1/products/";

    @Override
    public List<Order> getOrders() {
        return repo.findAll();
    }

    @Override
    public ResponseTemplateVo getOrder(Long id) {
        Order order = repo.findOneById(id);
        if (order == null) {
            throw new NotFoundException("Not found id " + id);
        } else {
            Product product = restTemplate.getForObject(URL_GET_PRODUCT + order.getProductId(), Product.class);
            Customer customer = restTemplate.getForObject(URL_GET_CUSTOMER + order.getCustomerId(), Customer.class);
            ResponseTemplateVo templateVo = new ResponseTemplateVo();
            templateVo.setOrder(order);
            templateVo.setCustomer(customer);
            templateVo.setProduct(product);
            return templateVo;
        }
    }

    @Override
    public Order create(Order order) {
        try {
            Product product = restTemplate.getForObject(URL_GET_PRODUCT + order.getProductId(), Product.class);
            Customer customer = restTemplate.getForObject(URL_GET_CUSTOMER + order.getCustomerId(), Customer.class);
            return repo.save(order);
        } catch (Exception e) {
            throw new NotFoundException("Not found product id or customer id");
        }
    }

    @Override
    public Order update(Order order) {
        order.setTime(LocalDateTime.now());
        return repo.save(order);
    }

    @Override
    public void delete(Long id) {
        Order order = repo.findOneById(id);
        if (order == null) {
            throw new NotFoundException("Not found id " + id);
        } else {
            repo.deleteById(id);
        }
    }
}
