package com.poc.domain.messaging.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.poc.persistence.entities.CashOutReport;
import com.poc.persistence.repositories.CashOutReportRepository;
import com.poc.web.models.CashOutReportRequest;

@Component
public class AnalyticsProducer {
	
	@Autowired
    private JmsTemplate jmsTemplate;
	
	@Autowired
    private CashOutReportRepository cashOutReportRepository;

	public void pushMessage(CashOutReportRequest cashOutReportRequest) {
		
		CashOutReport cashOutReport = cashOutReportRepository.findByYear(cashOutReportRequest.getYear());
		
		if (cashOutReport == null) {			
			jmsTemplate.convertAndSend(cashOutReportRequest);
		}
	}

}
