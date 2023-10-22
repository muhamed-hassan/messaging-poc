package com.poc.web.controllers;

import org.springframework.http.ResponseEntity;

import com.poc.web.models.CashOutReportRequest;

public interface AnalyticsController {

	ResponseEntity<Object> prepareReportRequest(CashOutReportRequest cashOutReportRequest);
	
	/*
     * ..
     * prepareReportRequest(ReportType1 reportType1);
     * prepareReportRequest(ReportType2 reportType2);
     * prepareReportRequest(ReportType3 reportType3);
     * 
     * Suggested Reference: 
     * https://en.wikipedia.org/wiki/Function_overloading
     * 
     * */

}
