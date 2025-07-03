package com.hackathon.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
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


}
