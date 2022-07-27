package com.banvien.order.vo;

import com.banvien.order.entity.Order;
import lombok.Data;

@Data
public class ResponseTemplateVo {
    private Order order;
    private Product product;
    private Customer customer;
}
