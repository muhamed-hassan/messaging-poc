package com.poc.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.domain.messaging.AnalyticsProducer;
import com.poc.interfaces.rest.models.CashOutReport;
import com.poc.interfaces.rest.models.CashOutReportRequest;
import com.poc.interfaces.rest.models.MonthlySpending;
import com.poc.persistence.repositories.CashOutReportRepository;

/*
 * Suggested Reference:
 * https://en.wikipedia.org/wiki/Facade_pattern 
 */
@Service
public class AnalyticsServiceImpl implements AnalyticsService {
	
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

	@Override
	public void prepareReportRequest(CashOutReportRequest cashOutReportRequest) {
		
		analyticsProducer.pushMessage(cashOutReportRequest);
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
	
	@Override
	public CashOutReport getCashOutReport(int year) {
		
		com.poc.persistence.entities.CashOutReport cashOutReport = cashOutReportRepository.findByYear(year);
		
		if (cashOutReport == null) {
			return null;
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
