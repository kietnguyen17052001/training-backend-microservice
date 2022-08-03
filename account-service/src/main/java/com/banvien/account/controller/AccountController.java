package com.banvien.account.controller;

import com.banvien.account.dto.AccountDto;
import com.banvien.account.entity.Account;
import com.banvien.account.entity.Role;
import com.banvien.account.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
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
    public ResponseEntity<List<AccountDto>> getAccount(@RequestParam(name = "search", required = false) String search,
                                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "3") int size,
                                                       @RequestParam(name = "sortBy", required = false) String sortBy) {
        List<Account> accounts = service.getAccounts(search, (page > 0) ? page - 1 : page, size, sortBy);
        List<AccountDto> accountDtos = accounts.stream().map(account -> mapper.map(account, AccountDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(accountDtos, HttpStatus.OK);
    }


    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id) {
        AccountDto dto = mapper.map(service.getAccount(id), AccountDto.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/accounts/username/{username}")
    public ResponseEntity<AccountDto> getAccountByUsername(@PathVariable("username") String username) {
        AccountDto dto = mapper.map(service.getAccountByUsername(username), AccountDto.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
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

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        service.deleteAccountById(id);
        return new ResponseEntity<>("Delete successful", HttpStatus.OK);
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        return new ResponseEntity<>(service.addRole(role), HttpStatus.CREATED);
    }
}
