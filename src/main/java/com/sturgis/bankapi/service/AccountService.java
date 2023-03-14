package com.sturgis.bankapi.service;

import com.sturgis.bankapi.entity.Account;
import com.sturgis.bankapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account newAccount(Account account){
        account.setBalance(15d);
        return accountRepository.save(account);
    }

    public List<Account> listAccount(){
        return accountRepository.findAll();
    }

    public void deleteAccountById(Long id){
        accountRepository.deleteById(id);
    }

    public Optional<Account> findAccountById(Long id){
        return accountRepository.findById(id);
    }

    public double deposit(double value, Long id){
        double balance;
        //accountRepository.save(id, balance );
        return 0;
    }
}
