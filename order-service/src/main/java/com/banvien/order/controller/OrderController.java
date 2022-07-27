package com.banvien.order.controller;

import com.banvien.order.entity.Order;
import com.banvien.order.service.OrderService;
import com.banvien.order.vo.ResponseTemplateVo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(service.getOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplateVo> getOrder(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getOrder(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return new ResponseEntity<>(service.create(order), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Order> update(@RequestBody Order order) {
        return new ResponseEntity<>(service.update(order), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("Delete successful", HttpStatus.OK);
    }

}
