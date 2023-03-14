package com.sturgis.bankapi.repository;

import com.sturgis.bankapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
