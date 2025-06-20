package com.hackathon.dto;

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

    // Getters and Setters
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerAccountNumber() { return customerAccountNumber; }
    public void setCustomerAccountNumber(String customerAccountNumber) { this.customerAccountNumber = customerAccountNumber; }

    public String getCustomerAddress() { return customerAddress; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
}
