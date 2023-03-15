package com.sturgis.bankapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sturgis.bankapi.entity.Account;
import com.sturgis.bankapi.entity.Customer;
import com.sturgis.bankapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    public ResponseEntity<?> deposit(double value, Long id){
        if(this.accountExists(id)) {
            Account account = this.findAccountById(id).get();
            account.setBalance(account.getBalance() + value);
            accountRepository.save(account);
            return ResponseEntity.ok(account);
        }else{
            return ResponseEntity.badRequest().body("Account does not exist");
        }

    }

    public ResponseEntity<?> withdraw(double value, Long id){
        if(this.accountExists(id)) {
            Account account = this.findAccountById(id).get();
            account.setBalance(account.getBalance() - value);
            if (account.getBalance() >= 0d) {
                accountRepository.save(account);
                return ResponseEntity.ok(account);
            }else {
                return ResponseEntity.badRequest().body("You can not withdraw this amount");
            }
        }else{
            return ResponseEntity.badRequest().body("Account does not exist");
        }

    }

    public ResponseEntity<?> transfer(double value, Long idTransference, Long idReceive){
        if (this.accountExists(idTransference) && this.accountExists(idReceive)){
            Account tranfer = this.findAccountById(idTransference).get();
            Account receive = this.findAccountById(idReceive).get();
            if (tranfer.getBalance() >= value){
                tranfer.setBalance(tranfer.getBalance() - value);
                receive.setBalance(receive.getBalance() + value);
                accountRepository.save(tranfer);
                accountRepository.save(receive);
                return ResponseEntity.ok(tranfer);
            }else {
                return ResponseEntity.badRequest().body("Your account does not have enough balance");
            }

        }else {
            return ResponseEntity.badRequest().body("Some account does not exist");
        }
    }

}
