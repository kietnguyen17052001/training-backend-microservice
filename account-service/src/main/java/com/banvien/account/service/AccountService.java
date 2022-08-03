package com.banvien.account.service;

import com.banvien.account.entity.Account;
import com.banvien.account.entity.Role;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    Account register(Account account);

    Account addRoleToAccount(String username, String nameRole);

    Role addRole(Role role);

    List<Account> getAccounts(String search, int page, int size, String sortBy);

    Account getAccount(Long id);

    Account getAccountByUsername(String username);

    void deleteAccountById(Long id);
}
