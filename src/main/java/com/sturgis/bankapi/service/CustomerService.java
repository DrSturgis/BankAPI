package com.sturgis.bankapi.service;

import com.sturgis.bankapi.entity.Customer;
import com.sturgis.bankapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer newCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> listCostumers(){
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public Optional<Customer> findById(Long id){
        return customerRepository.findById(id);
    }

    public void deleteCostumerById(Long id){
        customerRepository.deleteById(id);
    }
}
