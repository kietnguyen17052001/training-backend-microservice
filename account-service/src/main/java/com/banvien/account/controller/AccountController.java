package com.banvien.account.controller;

import com.banvien.account.entity.Account;
import com.banvien.account.entity.Role;
import com.banvien.account.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {
    private final AccountService service;

    @Data
    static class FormAddRole {
        private String username;
        private String nameRole;
    }

    @PostMapping("/accounts/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        return new ResponseEntity<>(service.register(account), HttpStatus.CREATED);
    }

    @PutMapping("/accounts/addRole")
    public ResponseEntity<?> addRole(@RequestBody FormAddRole form) {
        service.addRoleToAccount(form.getUsername(), form.getNameRole());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        return new ResponseEntity<>(service.addRole(role), HttpStatus.CREATED);
    }
}
