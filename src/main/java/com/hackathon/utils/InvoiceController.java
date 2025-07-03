package com.hackathon.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    /**
     * Get all invoices
     */
    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    /**
     * Get invoice by ID
     */
    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable String invoiceId) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(invoiceId);
        return invoice.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get invoices by customer ID
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Invoice>> getInvoicesByCustomerId(@PathVariable String customerId) {
        List<Invoice> invoices = invoiceService.getInvoicesByCustomerId(customerId);
        return ResponseEntity.ok(invoices);
    }

    /**
     * Get invoices by status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Invoice>> getInvoicesByStatus(@PathVariable String status) {
        List<Invoice> invoices = invoiceService.getInvoicesByStatus(status);
        return ResponseEntity.ok(invoices);
    }

    /**
     * Get invoice statistics
     */
    @GetMapping("/stats")
    public ResponseEntity<InvoiceService.InvoiceStats> getInvoiceStats() {
        InvoiceService.InvoiceStats stats = invoiceService.getInvoiceStats();
        return ResponseEntity.ok(stats);
    }

    /**
     * Get total invoice amount
     */
    @GetMapping("/total-amount")
    public ResponseEntity<Double> getTotalInvoiceAmount() {
        Double totalAmount = invoiceService.getTotalInvoiceAmount();
        return ResponseEntity.ok(totalAmount);
    }
}