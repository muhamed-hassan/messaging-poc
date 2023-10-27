package com.poc.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.domain.AnalyticsService;
import com.poc.web.models.CashOutReport;
import com.poc.web.models.CashOutReportRequest;
import com.poc.web.validators.Validator;

@RequestMapping("v1/analytics")
@RestController
public class AnalyticsControllerImpl implements AnalyticsController {
	
	@Autowired
	private AnalyticsService analyticsService;
	
	@Autowired
	private Validator validator; 

	@RequestMapping(value = "cash-out-report", method = RequestMethod.POST)
	@Override
	public ResponseEntity<Object> prepareReportRequest(@RequestBody CashOutReportRequest cashOutReportRequest) {
		
		validator.validate(cashOutReportRequest);
		
		analyticsService.prepareReportRequest(cashOutReportRequest);
		
		return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	}
	
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
	
	@RequestMapping(value = "cash-out-report/{year}", method = RequestMethod.GET)
	public ResponseEntity<Object> getCashOutReport(@PathVariable int year) {

		validator.validate(year);
				
		CashOutReport responseBody = analyticsService.getCashOutReport(year);
		
		return new ResponseEntity<Object>(responseBody, HttpStatus.OK);
	}
	
	/*
	 * ..
	 * getReportName1(argX_1 .. argX_N)
	 * getReportName2(argY_1 .. argY_N)
	 * getReportName3(argZ_1 .. argZ_N)
	 * 
	 * Suggested Reference: 
     * https://en.wikipedia.org/wiki/Function_overloading 
	 * 
	 */
	
}
