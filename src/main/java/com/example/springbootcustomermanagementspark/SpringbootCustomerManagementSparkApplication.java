package com.example.springbootcustomermanagementspark;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringbootCustomerManagementSparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCustomerManagementSparkApplication.class, args);
	}

}
