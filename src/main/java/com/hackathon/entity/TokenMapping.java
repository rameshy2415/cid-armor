package com.hackathon.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "token_mappings")
public class TokenMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "token_value")
    private String tokenValue;

    @Column(name = "field_value")
    private String fieldValue;

    // Constructors
    public TokenMapping() {}

    public TokenMapping(Long customerId, String fieldName, String tokenValue, String fieldValue) {
        this.customerId = customerId;
        this.fieldName = fieldName;
        this.tokenValue = tokenValue;
        this.fieldValue = fieldValue;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getFieldName() { return fieldName; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }

    public String getTokenValue() { return tokenValue; }
    public void setTokenValue(String tokenValue) { this.tokenValue = tokenValue; }

    public String getFieldValue() { return fieldValue; }
    public void setFieldValue(String fieldValue) { this.fieldValue = fieldValue; }
}
