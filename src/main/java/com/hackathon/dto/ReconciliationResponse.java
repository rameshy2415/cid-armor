package com.hackathon.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReconciliationResponse {
    private String requestId;
    private String status;
    private String message;
    private int processedRecords;
    private int matchedRecords;
    private int unmatchedRecords;

    public ReconciliationResponse() {}
}
