package com.hackathon.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDto {
    private String customerName;
    private String customerAccountNumber;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;

    // Constructors
    public CustomerDto() {}

    public CustomerDto(String customerName, String customerAccountNumber, String customerAddress,
                       String customerPhone, String customerEmail) {
        this.customerName = customerName;
        this.customerAccountNumber = customerAccountNumber;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }

}
