package com.banvien.account.service;

import com.banvien.account.entity.Account;
import com.banvien.account.entity.Role;

import java.util.List;

public interface AccountService {
    Account register(Account account);

    Account addRoleToAccount(String username, String nameRole);

    Role addRole(Role role);

    List<Account> getAccount(String search);

    Account getAccounts(Long id);
}
