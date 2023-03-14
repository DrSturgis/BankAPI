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

}
