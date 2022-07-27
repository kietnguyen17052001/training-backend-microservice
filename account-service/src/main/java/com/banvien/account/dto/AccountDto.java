package com.banvien.account.dto;

import com.banvien.account.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class AccountDto {
    private Long id;
    private String displayName;
    private String username;
    private List<Role> roles;
}
