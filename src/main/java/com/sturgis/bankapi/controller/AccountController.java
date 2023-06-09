package com.sturgis.bankapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sturgis.bankapi.entity.Account;
import com.sturgis.bankapi.entity.Customer;
import com.sturgis.bankapi.service.AccountService;
import com.sturgis.bankapi.service.CustomerService;
import com.sturgis.bankapi.service.HistoryTransferService;
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
    private HistoryTransferService historyTransferService;

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

    @DeleteMapping("/{id}/delete")
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

    @PostMapping("/{id}/deposit")
    public ResponseEntity<?> deposit(double value, @PathVariable("id") Long id){

        return accountService.deposit(value, id);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<?> withdraw(double value, @PathVariable("id") Long id){
        return accountService.withdraw(value, id);
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<?> transfer(double value, @PathVariable("id") Long idTransference, Long receiver){
        ResponseEntity<?> returnTransfer = accountService.transfer(value, idTransference, receiver);
        historyTransferService.newTransfer(idTransference, value, receiver);
        return returnTransfer;
    }

}
