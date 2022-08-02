package com.banvien.auth.service;

import com.banvien.auth.entity.Account;
import com.banvien.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final RestTemplate restTemplate;
    private final UserRepository repo;
    private final static String URL_GET_ACCOUNT_BY_USERNAME = "http://localhost:8083/api/v1/accounts/username/";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repo.findByUsername(username);
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        account.getRoles().forEach(role -> {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(account.getUsername(), account.getPassword(), simpleGrantedAuthorities);
    }
}
