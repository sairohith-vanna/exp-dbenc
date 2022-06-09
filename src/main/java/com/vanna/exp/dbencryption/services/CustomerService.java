package com.vanna.exp.dbencryption.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vanna.exp.dbencryption.models.Customer;
import com.vanna.exp.dbencryption.repositories.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomer(UUID customerId) {
        return customerRepository.findById(customerId);
    }
    
}
