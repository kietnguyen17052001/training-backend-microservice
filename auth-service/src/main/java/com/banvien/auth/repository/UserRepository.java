package com.banvien.auth.repository;

import com.banvien.auth.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
