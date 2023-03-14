package com.sturgis.bankapi.controller;

import com.sturgis.bankapi.entity.Customer;
import com.sturgis.bankapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
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

    @PostMapping("/update/{id}")
    public void updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        customerService.findById(id).map(customerBase -> {
            customerService.newCustomer(customer);
            return Void.TYPE;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found."));
    }
}
