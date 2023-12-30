package com.example.springbootcustomermanagementspark.model;


import javax.persistence.*;

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
    private String name;
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
