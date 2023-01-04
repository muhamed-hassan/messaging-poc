package com.poc.persistence.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "cash_out_report")
@Entity
public class CashOutReport {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private int year;
	
	@OneToMany(mappedBy = "masterReport", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // `LE` 12 records
	private List<MonthlySpending> monthlySpendings;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<MonthlySpending> getMonthlySpendings() {
		return monthlySpendings;
	}

	public void setMonthlySpendings(List<MonthlySpending> monthlySpendings) {
		this.monthlySpendings = monthlySpendings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CashOutReport other = (CashOutReport) obj;
		if (id != other.id)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
}
