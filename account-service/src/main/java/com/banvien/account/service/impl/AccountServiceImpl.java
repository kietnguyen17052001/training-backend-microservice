package com.banvien.account.service.impl;

import com.banvien.account.entity.Account;
import com.banvien.account.entity.Role;
import com.banvien.account.exception.BadRequestException;
import com.banvien.account.exception.NotFoundException;
import com.banvien.account.repository.AccountRepo;
import com.banvien.account.repository.RoleRepo;
import com.banvien.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder encoder;

    @Override
    public Account register(Account account) {
        try {
            account.setPassword(encoder.encode(account.getPassword()));
            return accountRepo.save(account);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public Account addRoleToAccount(String username, String nameRole) {
        Account account = accountRepo.findByUsername(username);
        Role role = roleRepo.findByName(nameRole);
        if (account == null || role == null) {
            throw new NotFoundException("Not found " + username + " or " + nameRole);
        }
        account.getRoles().add(role);
        return accountRepo.save(account);
    }

    @Override
    public Role addRole(Role role) {
        try {
            return roleRepo.save(role);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public List<Account> getAccounts(String search, int page, int size, String sortBy) {
        Pageable pageable = (sortBy == null) ? PageRequest.of(page, size) : PageRequest.of(page, size, Sort.by(sortBy));
        Page<Account> accounts;
        if (search == null) {
            accounts = accountRepo.findAll(pageable);
        } else {
            accounts = accountRepo.findByUsernameContaining(search, pageable);
            if (accounts.isEmpty()) {
                throw new NotFoundException("Not found " + search);
            }
        }
        return accounts.getContent();
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepo.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found id " + id);
        });
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepo.findByUsername(username);
    }

    @Override
    public void deleteAccountById(Long id) {
        if (getAccount(id) == null) {
            throw new NotFoundException("Not found " + id);
        }
        accountRepo.deleteById(id);
    }

}
