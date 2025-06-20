package com.hackathon.dto;

import java.util.Map;

public class ReportDto {
    private Map<String, String> tokenizedData;
    private Map<String, String> actualData;
    private String reportType;

    public ReportDto() {}

    public ReportDto(Map<String, String> tokenizedData, Map<String, String> actualData, String reportType) {
        this.tokenizedData = tokenizedData;
        this.actualData = actualData;
        this.reportType = reportType;
    }

    public Map<String, String> getTokenizedData() { return tokenizedData; }
    public void setTokenizedData(Map<String, String> tokenizedData) { this.tokenizedData = tokenizedData; }

    public Map<String, String> getActualData() { return actualData; }
    public void setActualData(Map<String, String> actualData) { this.actualData = actualData; }

    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }
}
