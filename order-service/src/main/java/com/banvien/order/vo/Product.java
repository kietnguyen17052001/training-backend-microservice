package com.banvien.order.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    private Long id;
    private String name;
    private float cost;
}
