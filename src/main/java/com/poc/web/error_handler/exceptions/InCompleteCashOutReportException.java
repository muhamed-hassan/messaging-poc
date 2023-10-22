package com.poc.web.error_handler.exceptions;

public class InCompleteCashOutReportException extends RuntimeException {
	
	public InCompleteCashOutReportException() {
		super("cash-out report not found, just try at another time ...");
	}

}
