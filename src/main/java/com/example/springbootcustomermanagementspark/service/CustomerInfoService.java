package com.example.springbootcustomermanagementspark.service;

import com.example.springbootcustomermanagementspark.model.Customer;
import com.example.springbootcustomermanagementspark.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerInfoService implements UserDetailsService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> userDetail = repository.findByName(username);

        // Converting userDetail to UserDetails
        return userDetail.map(CustomerInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addCustomer(Customer userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "Customer Added Successfully";
    }


}


