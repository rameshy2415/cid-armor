package com.hackathon.service;

import com.hackathon.dto.CustomerDto;
import com.hackathon.dto.TokenizedCustomerDto;
import com.hackathon.entity.Customer;
import com.hackathon.entity.TokenMapping;
import com.hackathon.repository.CustomerRepository;
import com.hackathon.repository.TokenMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@Transactional
public class CidService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TokenMappingRepository tokenMappingRepository;

    @Autowired
    private TokenService tokenService;

    public TokenizedCustomerDto storeCustomerWithTokens(CustomerDto customerDto) {
        // Save customer
        Customer customer = new Customer(
                customerDto.getCustomerName(),
                customerDto.getCustomerAccountNumber(),
                customerDto.getCustomerAddress(),
                customerDto.getCustomerPhone(),
                customerDto.getCustomerEmail()
        );

        Customer savedCustomer = customerRepository.save(customer);

        // Generate tokens for each field
        Map<String, String> tokens = new HashMap<>();

        // Create token mappings
        createTokenMapping(savedCustomer.getId(), "customerName", savedCustomer.getCustomerName(), tokens);
        createTokenMapping(savedCustomer.getId(), "customerAccountNumber", savedCustomer.getCustomerAccountNumber(), tokens);
        createTokenMapping(savedCustomer.getId(), "customerAddress", savedCustomer.getCustomerAddress(), tokens);
        createTokenMapping(savedCustomer.getId(), "customerPhone", savedCustomer.getCustomerPhone(), tokens);
        createTokenMapping(savedCustomer.getId(), "customerEmail", savedCustomer.getCustomerEmail(), tokens);

        return new TokenizedCustomerDto(savedCustomer.getId(), tokens);
    }

    private void createTokenMapping(Long customerId, String fieldName, String fieldValue, Map<String, String> tokens) {
        if (fieldValue != null && !fieldValue.trim().isEmpty()) {
            String token = tokenService.generateUniqueToken();
            TokenMapping mapping = new TokenMapping(customerId, fieldName, token, fieldValue);
            tokenMappingRepository.save(mapping);
            tokens.put(fieldName, token);
        }
    }

    public Map<String, String> getActualValuesFromTokens(Map<String, String> tokenMap) {
        Map<String, String> actualValues = new HashMap<>();

        for (Map.Entry<String, String> entry : tokenMap.entrySet()) {
            String fieldName = entry.getKey();
            String token = entry.getValue();

            Optional<TokenMapping> mapping = tokenMappingRepository.findByTokenValue(token);
            if (mapping.isPresent()) {
                actualValues.put(fieldName, mapping.get().getFieldValue());
            } else {
                actualValues.put(fieldName, "TOKEN_NOT_FOUND");
            }
        }

        return actualValues;
    }

    public String getActualValueByToken(String token) {
        Optional<TokenMapping> mapping = tokenMappingRepository.findByTokenValue(token);
        return mapping.map(TokenMapping::getFieldValue).orElse("TOKEN_NOT_FOUND");
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<TokenMapping> getAllTokenMappings() {
        return tokenMappingRepository.findAll();
    }
}
