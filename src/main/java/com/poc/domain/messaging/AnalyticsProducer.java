package com.poc.domain.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.poc.interfaces.rest.models.CashOutReportRequest;
import com.poc.persistence.entities.CashOutReport;
import com.poc.persistence.repositories.CashOutReportRepository;

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
			jmsTemplate.send(new MessageCreator() {
				
				@Override
				public Message createMessage(Session session) throws JMSException {
					ObjectMessage objectMessage = session.createObjectMessage(cashOutReportRequest); 
					return objectMessage;
				}
			});
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
