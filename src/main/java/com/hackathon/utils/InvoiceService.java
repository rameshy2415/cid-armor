package com.hackathon.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
    private final ObjectMapper objectMapper;

    public InvoiceService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Read all invoices from JSON file
     */
    public List<Invoice> getAllInvoices() {
        try {
            ClassPathResource resource = new ClassPathResource("data/invoices.json");
            InputStream inputStream = resource.getInputStream();

            List<Invoice> invoices = objectMapper.readValue(inputStream,
                    new TypeReference<List<Invoice>>() {});

            logger.info("Successfully loaded {} invoices from JSON file", invoices.size());
            return invoices;

        } catch (IOException e) {
            logger.error("Error reading invoices from JSON file: {}", e.getMessage());
            throw new RuntimeException("Failed to load invoices", e);
        }
    }

    /**
     * Find invoice by ID
     */
    public Optional<Invoice> getInvoiceById(String invoiceId) {
        return getAllInvoices().stream()
                .filter(invoice -> invoice.getInvoiceId().equals(invoiceId))
                .findFirst();
    }

    /**
     * Find invoices by customer ID
     */
    public List<Invoice> getInvoicesByCustomerId(String customerId) {
        return getAllInvoices().stream()
                .filter(invoice -> invoice.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    /**
     * Find invoices by status
     */
    public List<Invoice> getInvoicesByStatus(String status) {
        return getAllInvoices().stream()
                .filter(invoice -> invoice.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    /**
     * Calculate total amount for all invoices
     */
    public Double getTotalInvoiceAmount() {
        return getAllInvoices().stream()
                .mapToDouble(Invoice::getTotalAmount)
                .sum();
    }

    /**
     * Get invoice statistics
     */
    public InvoiceStats getInvoiceStats() {
        List<Invoice> invoices = getAllInvoices();

        long totalCount = invoices.size();
        long paidCount = invoices.stream()
                .filter(i -> "PAID".equalsIgnoreCase(i.getStatus()))
                .count();
        long pendingCount = invoices.stream()
                .filter(i -> "PENDING".equalsIgnoreCase(i.getStatus()))
                .count();

        double totalAmount = invoices.stream()
                .mapToDouble(Invoice::getTotalAmount)
                .sum();

        return new InvoiceStats(totalCount, paidCount, pendingCount, totalAmount);
    }

    // Inner class for statistics
    public static class InvoiceStats {
        private final long totalInvoices;
        private final long paidInvoices;
        private final long pendingInvoices;
        private final double totalAmount;

        public InvoiceStats(long totalInvoices, long paidInvoices,
                            long pendingInvoices, double totalAmount) {
            this.totalInvoices = totalInvoices;
            this.paidInvoices = paidInvoices;
            this.pendingInvoices = pendingInvoices;
            this.totalAmount = totalAmount;
        }

        // Getters
        public long getTotalInvoices() { return totalInvoices; }
        public long getPaidInvoices() { return paidInvoices; }
        public long getPendingInvoices() { return pendingInvoices; }
        public double getTotalAmount() { return totalAmount; }
    }
}
