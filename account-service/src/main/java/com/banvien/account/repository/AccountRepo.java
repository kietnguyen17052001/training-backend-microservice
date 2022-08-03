package com.banvien.account.repository;

import com.banvien.account.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByUsername(String username);

    Page<Account> findByUsernameContaining(String search, Pageable pageable);
}
