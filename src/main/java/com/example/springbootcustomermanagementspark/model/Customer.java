package com.example.springbootcustomermanagementspark.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @NotBlank(message = "Please enter proper employee name")
    @Size(min=5, message = "Name should be atleast 5 characters")
    @Size(max=12, message = "Name should not be greater than 12 characters")
    private String name;

    @Email(message = "Please enter a valid email Id", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
    @NotNull(message = "Please enter a valid email Id")
    private String email;
    
    private long phone;

    private String address;

    private String password;

    private String roles;
    private String companyName;
    private String industryType;
    private boolean customerStatus;
    private String accountManager;
    private String createdAt;
    private String updatedAt;
}
