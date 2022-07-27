package com.banvien.auth.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tbl_role")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
