package com.hackathon.controller;

import com.hackathon.dto.CustomerDto;
import com.hackathon.dto.ReportDto;
import com.hackathon.dto.TokenizedCustomerDto;
import com.hackathon.entity.Customer;
import com.hackathon.entity.TokenMapping;
import com.hackathon.service.CidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cid")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CidController {

    @Autowired
    private CidService cidService;

    @PostMapping("/customer")
    public ResponseEntity<TokenizedCustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        TokenizedCustomerDto result = cidService.storeCustomerWithTokens(customerDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/detokenize")
    public ResponseEntity<Map<String, String>> detokenizeData(@RequestBody Map<String, String> tokenMap) {
        Map<String, String> actualValues = cidService.getActualValuesFromTokens(tokenMap);
        return ResponseEntity.ok(actualValues);
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<String> getValueByToken(@PathVariable String token) {
        String actualValue = cidService.getActualValueByToken(token);
        return ResponseEntity.ok(actualValue);
    }

    @PostMapping("/generate-report")
    public ResponseEntity<ReportDto> generateReport(@RequestBody Map<String, String> tokenizedData) {
        Map<String, String> actualData = cidService.getActualValuesFromTokens(tokenizedData);
        ReportDto report = new ReportDto(tokenizedData, actualData, "Customer Report");
        return ResponseEntity.ok(report);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = cidService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Customer>> getCustomerById(@PathVariable("id") String id) {
        List<Customer> customers = cidService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/tokens")
    public ResponseEntity<List<TokenMapping>> getAllTokenMappings() {
        List<TokenMapping> mappings = cidService.getAllTokenMappings();
        return ResponseEntity.ok(mappings);
    }
}
