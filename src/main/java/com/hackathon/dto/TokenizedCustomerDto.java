package com.hackathon.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class TokenizedCustomerDto {
    private Long customerId;
    private Map<String, String> tokens;

    public TokenizedCustomerDto() {}

    public TokenizedCustomerDto(Long customerId, Map<String, String> tokens) {
        this.customerId = customerId;
        this.tokens = tokens;
    }
}
