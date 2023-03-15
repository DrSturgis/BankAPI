package com.sturgis.bankapi.controller;

import com.sturgis.bankapi.entity.Customer;
import com.sturgis.bankapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer newUser(Customer customer){
        return customerService.newCustomer(customer);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> listCustomer(){
        return customerService.listCostumers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer findCustomerById(@PathVariable("id") Long id){
        return customerService.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found."));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        customerService.findById(id).map(customer -> {
            customerService.deleteCostumerById(id);
            return Void.TYPE;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found."));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, Customer customer){
        Optional<Customer> cust = customerService.findById(id);
        Customer c = cust.get();
        if(customer.getName() != null){
            c.setName(customer.getName());
            System.out.println(c.getName());
        }
        if(customer.getEmail() != null){
            c.setEmail(customer.getEmail());
            System.out.println(c.getEmail());
        }
        if (customer.getCpf() != null || customer.getBirth() != null){
            return ResponseEntity.badRequest().body("You can't change CPF and Birth.");
        }else {
            customerService.newCustomer(c);
            return ResponseEntity.ok(c);
        }

    }

}
