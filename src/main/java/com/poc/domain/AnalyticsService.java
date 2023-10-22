package com.poc.domain;

import com.poc.web.models.CashOutReport;
import com.poc.web.models.CashOutReportRequest;

public interface AnalyticsService {
	
	void prepareReportRequest(CashOutReportRequest cashOutReportRequest);
	
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
	
	CashOutReport getCashOutReport(int year);

}
