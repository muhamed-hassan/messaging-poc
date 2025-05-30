package com.poc.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.web.error_handler.exceptions.InCompleteCashOutReportException;
import com.poc.web.models.CashOutReport;
import com.poc.web.models.CashOutReportRequest;
import com.poc.web.models.MonthlySpending;
import com.poc.domain.messaging.producers.AnalyticsProducer;
import com.poc.persistence.repositories.CashOutReportRepository;

/*
 * Suggested Reference:
 * https://en.wikipedia.org/wiki/Facade_pattern 
 */
@Service
public class AnalyticsService {
	
	@Autowired
	private AnalyticsProducer analyticsProducer;
	
	@Autowired
    private CashOutReportRepository cashOutReportRepository;
	
	/*
	 * ..
	 * 
	 * @Autowired
	 * private ReportNameRepository1 reportNameRepository1;
	 * 
	 * @Autowired
	 * private ReportNameRepository2 reportNameRepository2;
	 * 
	 * @Autowired
	 * private ReportNameRepository3 reportNameRepository3;
	 * 
	 */

	public void prepareReportRequest(CashOutReportRequest cashOutReportRequest) {
		
		analyticsProducer.pushMessage(cashOutReportRequest);
	}
	
	public CashOutReport getCashOutReport(int year) {
		
		com.poc.persistence.entities.CashOutReport cashOutReport = cashOutReportRepository.findByYear(year);
		
		if (cashOutReport == null) {
			throw new InCompleteCashOutReportException();
		}
		
		CashOutReport cashOutReportModel = new CashOutReport();
		float summation = 0f;
		List<com.poc.persistence.entities.MonthlySpending> monthlySpendings = cashOutReport.getMonthlySpendings();
		List<MonthlySpending> monthlySpendingsModels = new ArrayList<MonthlySpending>();
		
		Iterator<com.poc.persistence.entities.MonthlySpending> iterator = monthlySpendings.iterator();
		while (iterator.hasNext()) {
			
			com.poc.persistence.entities.MonthlySpending currentElement = iterator.next();
			
			MonthlySpending monthlySpendingModel = new MonthlySpending();
			monthlySpendingModel.setAmount(currentElement.getAmount());
			monthlySpendingModel.setMonth(currentElement.getMonth());
			summation += currentElement.getAmount();
			monthlySpendingsModels.add(monthlySpendingModel);
		}
		cashOutReportModel.setMonthlySpendings(monthlySpendingsModels);
		cashOutReportModel.setSummation(summation);
		
		return cashOutReportModel;
	}
	
}
