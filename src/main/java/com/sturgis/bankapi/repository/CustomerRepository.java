package com.sturgis.bankapi.repository;

import com.sturgis.bankapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
