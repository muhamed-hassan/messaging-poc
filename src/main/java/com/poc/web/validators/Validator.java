package com.poc.web.validators;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.poc.web.models.CashOutReportRequest;

//https://en.wikipedia.org/wiki/Fail-fast approach is used to report validation errors
@Component
public class Validator {

	public void validate(CashOutReportRequest cashOutReportRequest) {
		
		// validate "cashOutReportRequest.year range [(currentYear - 5), currentYear] only 5 years backwards is allowed"
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		if (cashOutReportRequest.getYear() > currentYear || cashOutReportRequest.getYear() < (currentYear - 5)) {
			throw new IllegalArgumentException("only 5 years backwards is allowed");
		}
	}
	
	public void validate(int year) {
		
		// validate "year range [(currentYear - 5), currentYear] only 5 years backwards is allowed"
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		if (year > currentYear || year < (currentYear - 5)) {
			throw new IllegalArgumentException("only 5 years backwards is allowed");
		}
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
