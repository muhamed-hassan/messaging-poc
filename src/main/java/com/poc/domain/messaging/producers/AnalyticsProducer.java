package com.poc.domain.messaging.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.poc.domain.messaging.MessageProducer;
import com.poc.persistence.entities.CashOutReport;
import com.poc.persistence.repositories.CashOutReportRepository;
import com.poc.web.models.CashOutReportRequest;

@Component
public class AnalyticsProducer implements MessageProducer {
	
	@Autowired
    private JmsTemplate jmsTemplate;
	
	@Autowired
    private CashOutReportRepository cashOutReportRepository;

	@Override
	public void pushMessage(CashOutReportRequest cashOutReportRequest) {
		
		CashOutReport cashOutReport = cashOutReportRepository.findByYear(cashOutReportRequest.getYear());
		
		if (cashOutReport == null) {			
			jmsTemplate.convertAndSend(cashOutReportRequest);
		}
	}
	
	/*
     * ..
     * pushMessage(ReportType1 reportType1);
     * pushMessage(ReportType2 reportType2);
     * pushMessage(ReportType3 reportType3);
     * 
     * Suggested Reference: 
     * https://en.wikipedia.org/wiki/Function_overloading 
     * 
     * */

}
