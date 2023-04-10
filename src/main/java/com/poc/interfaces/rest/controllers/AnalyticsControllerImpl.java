package com.poc.interfaces.rest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.domain.AnalyticsService;
import com.poc.interfaces.rest.models.CashOutReport;
import com.poc.interfaces.rest.models.CashOutReportRequest;

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
		
		String errorMessage = validator.validate(cashOutReportRequest);
		if (errorMessage != null) {			
			Map<String, String> error = new HashMap<String, String>(1);
			error.put("error", errorMessage);			
			return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
		}
		
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

		String errorMessage = validator.validate(year);
		if (errorMessage != null) {			
			Map<String, String> error = new HashMap<String, String>(1);
			error.put("error", errorMessage);			
			return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
		}
				
		CashOutReport responseBody = analyticsService.getCashOutReport(year);
		if (responseBody == null) {
			Map<String, String> warning = new HashMap<String, String>(1);
			warning.put("warning", "cash-out report not found, just try at another time ...");			
			return new ResponseEntity<Object>(warning, HttpStatus.NOT_FOUND);
		}
		
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
