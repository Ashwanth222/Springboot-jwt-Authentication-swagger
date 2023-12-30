package com.example.springbootcustomermanagementspark.repository;

import com.example.springbootcustomermanagementspark.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByName(String name);
}