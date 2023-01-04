package com.poc.interfaces.rest.models;

import java.io.Serializable;

public class CashOutReportRequest implements Serializable {

	private int year;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "CashOutReportRequest [year=" + year + "]";
	}

}
