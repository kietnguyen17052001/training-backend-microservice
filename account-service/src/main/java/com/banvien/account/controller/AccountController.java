package com.banvien.account.controller;

import com.banvien.account.dto.AccountDto;
import com.banvien.account.entity.Account;
import com.banvien.account.entity.Role;
import com.banvien.account.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {
    private final AccountService service;
    private final ModelMapper mapper;

    @Data
    static class FormAddRole {
        private String username;
        private String nameRole;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccount(@RequestParam(name = "search", required = false) String search) {
        List<Account> accounts = service.getAccount(search);
        List<AccountDto> accountDtos = accounts.stream().map(account -> mapper.map(account, AccountDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(accountDtos, HttpStatus.OK);
    }


    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("id") Long id) {
        AccountDto dto = mapper.map(service.getAccounts(id), AccountDto.class);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/accounts/register")
    public ResponseEntity<AccountDto> register(@RequestBody Account account) {
        AccountDto dto = mapper.map(service.register(account), AccountDto.class);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/accounts/addRole")
    public ResponseEntity<AccountDto> addRole(@RequestBody FormAddRole form) {
        AccountDto dto = mapper.map(service.addRoleToAccount(form.getUsername(), form.getNameRole()), AccountDto.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        return new ResponseEntity<>(service.addRole(role), HttpStatus.CREATED);
    }
}
