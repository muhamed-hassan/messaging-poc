package com.poc.web.models;

import java.util.List;

public class CashOutReport {
	
	private float summation;
	
	private List<MonthlySpending> monthlySpendings;	

	public float getSummation() {
		return summation;
	}

	public void setSummation(float summation) {
		this.summation = summation;
	}

	public List<MonthlySpending> getMonthlySpendings() {
		return monthlySpendings;
	}

	public void setMonthlySpendings(List<MonthlySpending> monthlySpendings) {
		this.monthlySpendings = monthlySpendings;
	}
	
}
