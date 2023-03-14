package com.sturgis.bankapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sturgis.bankapi.entity.Account;
import com.sturgis.bankapi.entity.Customer;
import com.sturgis.bankapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<?> newAccount(Account account, Customer c, String type){

        account.setBalance(0d);
        account.setActivated(true);
        account.setCustomer(c);
        System.out.println(type);
        if(type.equals("CC")){
            account.setTypeAcc("CC");
        }
        if (type.equals("POUP")){
            account.setTypeAcc("POUP");
        }
        try {
            return ResponseEntity.ok(accountRepository.save(account));
        } catch (Error e) {
            return ResponseEntity.badRequest().body(false);
        }
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

    public boolean accountExists(Long id){
        Optional<Account> account = this.findAccountById(id);
        if (account.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public Optional<Account> statement(Long id){

        return this.findAccountById(id);
    }

    public double deposit(double value, Long id){
        double balance;
        //accountRepository.save(id, balance );
        return 0;
    }
}
