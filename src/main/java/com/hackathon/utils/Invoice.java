package com.hackathon.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;

public class Invoice {
    @JsonProperty("invoiceId")
    private String invoiceId;

    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("invoiceDate")
    private LocalDate invoiceDate;

    @JsonProperty("dueDate")
    private LocalDate dueDate;

    @JsonProperty("totalAmount")
    private Double totalAmount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("items")
    private List<InvoiceItem> items;

    // Constructors
    public Invoice() {}

    public Invoice(String invoiceId, String customerId, String customerName,
                   LocalDate invoiceDate, LocalDate dueDate, Double totalAmount,
                   String status, List<InvoiceItem> items) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.items = items;
    }

    // Getters and Setters
    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public LocalDate getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<InvoiceItem> getItems() { return items; }
    public void setItems(List<InvoiceItem> items) { this.items = items; }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + invoiceId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", invoiceDate=" + invoiceDate +
                ", dueDate=" + dueDate +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", items=" + items +
                '}';
    }
}
