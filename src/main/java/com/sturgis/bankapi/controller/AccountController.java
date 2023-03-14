package com.sturgis.bankapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sturgis.bankapi.entity.Account;
import com.sturgis.bankapi.entity.Customer;
import com.sturgis.bankapi.service.AccountService;
import com.sturgis.bankapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public String newAccount(Account account, Long id, String type){
        Optional<Customer> customer = customerService.findById(id);
        Customer c = customer.get();
        ResponseEntity acc = accountService.newAccount(account, c,type);

        //System.out.println(acc);
        return "Account created";
    }

    @GetMapping("/list")
    public List<Account> listAccounts(){
        return accountService.listAccount();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAccountById(@PathVariable("id") Long id){
        Optional<Account> opt = accountService.findAccountById(id);
        if (opt.isEmpty()){
            return "Account does not exist";
        }else{
            accountService.deleteAccountById(id);
            return "Account deleted";
        }

    }

    @GetMapping("/{id}/statement")
    public ResponseEntity<?> statement(@PathVariable("id") Long id){
        boolean exists = accountService.accountExists(id);
        if( exists == true){
            Account account = accountService.statement(id).get();
            return ResponseEntity.ok(account);
        }else {
            return ResponseEntity.badRequest().body("Account ID does not exist");
        }

    }

}
