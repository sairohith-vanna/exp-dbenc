package com.vanna.exp.dbencryption.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanna.exp.dbencryption.models.Customer;
import com.vanna.exp.dbencryption.services.CustomerService;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        var createdCustomer = customerService.addCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }
    
    @GetMapping("{customerId}")
    public ResponseEntity<Customer> createCustomer(@PathVariable("customerId") UUID customerId) {
        var customer = customerService.getCustomer(customerId);
        return ResponseEntity.ok(customer.orElse(null));
    }
}
