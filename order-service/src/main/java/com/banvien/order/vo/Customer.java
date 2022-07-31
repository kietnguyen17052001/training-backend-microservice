package com.banvien.order.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {
    private Long id;
    private String name;
}
