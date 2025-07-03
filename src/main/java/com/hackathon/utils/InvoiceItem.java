package com.hackathon.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceItem {
    @JsonProperty("itemId")
    private String itemId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("unitPrice")
    private Double unitPrice;

    @JsonProperty("totalPrice")
    private Double totalPrice;

    // Constructors
    public InvoiceItem() {}

    public InvoiceItem(String itemId, String description, Integer quantity,
                       Double unitPrice, Double totalPrice) {
        this.itemId = itemId;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "itemId='" + itemId + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
