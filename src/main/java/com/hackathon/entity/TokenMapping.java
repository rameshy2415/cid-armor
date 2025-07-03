package com.hackathon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "token_mappings")
@Setter
@Getter
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
}
