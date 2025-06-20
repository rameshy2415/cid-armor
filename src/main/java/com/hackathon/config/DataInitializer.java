package com.hackathon.config;

import com.hackathon.dto.CustomerDto;
import com.hackathon.service.CidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CidService cidService;

    @Override
    public void run(String... args) throws Exception {
        // Initialize sample data
        CustomerDto customer1 = new CustomerDto(
                "Ramesh Yadav",
                "ACC123456789",
                "123 Main Street, Mumbai, Maharashtra",
                "+91-9876543210",
                "ramesh.yadav@email.com"
        );

        CustomerDto customer2 = new CustomerDto(
                "Bhimesh Solanki",
                "ACC987654321",
                "456 Park Avenue, Mumbai, Maharashtra",
                "+91-8765432109",
                "bhimesh.solanki@email.com"
        );

        cidService.storeCustomerWithTokens(customer1);
        cidService.storeCustomerWithTokens(customer2);

        System.out.println("Sample data initialized successfully!");
    }
}
