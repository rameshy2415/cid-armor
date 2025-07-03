package com.hackathon.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_account_number")
    private String customerAccountNumber;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_email")
    private String customerEmail;

    // Constructors
    public Customer() {}

    public Customer(String customerName, String customerAccountNumber, String customerAddress,
                    String customerPhone, String customerEmail) {
        this.customerName = customerName;
        this.customerAccountNumber = customerAccountNumber;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }
}
