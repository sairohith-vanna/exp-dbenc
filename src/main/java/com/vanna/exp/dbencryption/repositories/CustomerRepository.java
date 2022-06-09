package com.vanna.exp.dbencryption.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vanna.exp.dbencryption.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    
}
