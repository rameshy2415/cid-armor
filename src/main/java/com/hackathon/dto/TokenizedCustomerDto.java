package com.hackathon.dto;

import java.util.Map;

public class TokenizedCustomerDto {
    private Long customerId;
    private Map<String, String> tokens;

    public TokenizedCustomerDto() {}

    public TokenizedCustomerDto(Long customerId, Map<String, String> tokens) {
        this.customerId = customerId;
        this.tokens = tokens;
    }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Map<String, String> getTokens() { return tokens; }
    public void setTokens(Map<String, String> tokens) { this.tokens = tokens; }
}
