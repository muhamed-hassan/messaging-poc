package com.poc.interfaces.rest.controllers;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.poc.interfaces.rest.models.CashOutReportRequest;

//https://en.wikipedia.org/wiki/Fail-fast approach is used to report validation errors
@Component
public class Validator {

	public String validate(CashOutReportRequest cashOutReportRequest) {
		
		// validate "cashOutReportRequest.year range [(currentYear - 5), currentYear] only 5 years backwards is allowed"
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		if (cashOutReportRequest.getYear() > currentYear || cashOutReportRequest.getYear() < (currentYear - 5)) {
			return "only 5 years backwards is allowed";
		}
			
		return null;
	}
	
	public String validate(int year) {
		
		// validate "year range [(currentYear - 5), currentYear] only 5 years backwards is allowed"
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		if (year > currentYear || year < (currentYear - 5)) {
			return "only 5 years backwards is allowed";
		}
			
		return null;
	}
	
	/*
	 * ..
	 * validate(argX_1 .. argX_N)
	 * validate(argY_1 .. argY_N)
	 * validate(argZ_1 .. argZ_N)
	 * 
	 * Suggested Reference: 
     * https://en.wikipedia.org/wiki/Function_overloading
     *  
	 */
	
}
