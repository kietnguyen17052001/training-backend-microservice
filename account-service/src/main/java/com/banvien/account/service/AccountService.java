package com.banvien.account.service;

import com.banvien.account.entity.Account;
import com.banvien.account.entity.Role;

public interface AccountService {
    Account register(Account account);

    void addRoleToAccount(String username, String nameRole);

    Role addRole(Role role);
}