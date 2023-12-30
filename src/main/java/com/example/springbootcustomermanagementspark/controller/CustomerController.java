package com.example.springbootcustomermanagementspark.controller;

import com.example.springbootcustomermanagementspark.model.AuthRequest;
import com.example.springbootcustomermanagementspark.model.Customer;
import com.example.springbootcustomermanagementspark.repository.CustomerRepository;
import com.example.springbootcustomermanagementspark.service.CustomerInfoService;
import com.example.springbootcustomermanagementspark.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Tag(name = "Clients")
public class CustomerController {
    @Autowired
    private CustomerInfoService service;

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    @Operation(summary = "This method is used to get the clients.")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewCustomer")
    public String addNewUser(@RequestBody Customer customer) {
        return service.addCustomer(customer);
    }

    @GetMapping("/customer/customerProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String customerProfile() {
        return "Welcome to customer Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @GetMapping("/getById/{customerId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Customer> getTutorialById(@PathVariable("customerId") Integer customerId) {
        Optional<Customer> customer = repository.findById(customerId);

        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{customerId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Customer> updateTutorial(@PathVariable("customerId") Integer customerId, @RequestBody Customer customer) {
        Optional<Customer> customerData = repository.findById(customerId);

        if (customerData.isPresent()) {
            Customer customer1 = customerData.stream().findFirst().get();
            customer1.setEmail(customer.getEmail());
            customer1.setAddress(customer.getAddress());
            customer1.setUpdatedAt(customer.getUpdatedAt());
            customer1.setCreatedAt(customer.getCreatedAt());
            customer1.setPassword(customer.getPassword());
            customer1.setAccountManager(customer.getAccountManager());
            customer1.setCompanyName(customer.getCompanyName());
            customer1.setName(customer.getName());
            customer1.setRoles(customer.getRoles());
            customer1.setPhone(customer.getPhone());
            customer1.setIndustryType(customer.getIndustryType());
            customer1.setCustomerStatus(customer.isCustomerStatus());
            return new ResponseEntity<>(repository.save(customer1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{customerId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("customerId") int customerId) {
        try {
            repository.deleteById(customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
