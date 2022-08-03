package com.banvien.order.service;

import com.banvien.order.entity.Order;
import com.banvien.order.vo.ResponseTemplateVo;

import java.util.List;

public interface OrderService {
    List<Order> getOrders(int page, int size, String sortBy);

    ResponseTemplateVo getOrder(Long id);

    Order create(Order order);

    Order update(Order order);

    void delete(Long id);
}
