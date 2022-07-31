package com.banvien.order.vo;

import com.banvien.order.entity.Order;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseTemplateVo implements Serializable {
    private Order order;
    private Product product;
    private Customer customer;
}
