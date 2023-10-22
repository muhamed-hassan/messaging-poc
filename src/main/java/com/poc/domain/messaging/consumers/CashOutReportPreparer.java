package com.poc.domain.messaging.consumers;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.poc.web.models.CashOutReportRequest;
import com.poc.persistence.entities.CashOutReport;
import com.poc.persistence.entities.MonthlySpending;
import com.poc.persistence.repositories.CashOutReportRepository;

/*
 * Preparer for 1 report type ONLY
 * Just implement MessageListener
 */
public class CashOutReportPreparer implements MessageListener {

	@Autowired
    private CashOutReportRepository cashOutReportRepository;    

	@Override
	public void onMessage(Message message) {
		
		ObjectMessage objectMessage = (ObjectMessage) message;		
		CashOutReportRequest cashOutReportRequest = null;
		
		try {
			
			cashOutReportRequest = (CashOutReportRequest) objectMessage.getObject();
			
			// do 10 seconds delay before write - simulate `in-progress` state during preparation via messaging
			try {
				long delay = 10000L;
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}			
			/*
			 * adding some fake data which represents analytics that SHOULD be based on multiple complex queries 
			 * in real world applications which is simulated via `in-progress` state delay of 10 seconds,
			 * however this is just for demo purposes and this technique is abstracted in real life 
			 * */			
			CashOutReport cashOutReport = new CashOutReport();
			cashOutReport.setYear(cashOutReportRequest.getYear());
			
			List<MonthlySpending> monthlySpendings = new ArrayList<MonthlySpending>();
			
			MonthlySpending januaryMonthlySpending = new MonthlySpending();
			januaryMonthlySpending.setMasterReport(cashOutReport);
			januaryMonthlySpending.setMonth("January");
			januaryMonthlySpending.setAmount(777);
			monthlySpendings.add(januaryMonthlySpending);
			
			MonthlySpending februaryMonthlySpending = new MonthlySpending();
			februaryMonthlySpending.setMasterReport(cashOutReport);
			februaryMonthlySpending.setMonth("February");
			februaryMonthlySpending.setAmount(999);
			monthlySpendings.add(februaryMonthlySpending);
			
			MonthlySpending marchMonthlySpending = new MonthlySpending();
			marchMonthlySpending.setMasterReport(cashOutReport);
			marchMonthlySpending.setMonth("March");
			marchMonthlySpending.setAmount(5000);
			monthlySpendings.add(marchMonthlySpending);
			
			MonthlySpending aprilMonthlySpending = new MonthlySpending();
			aprilMonthlySpending.setMasterReport(cashOutReport);
			aprilMonthlySpending.setMonth("April");
			aprilMonthlySpending.setAmount(4500);
			monthlySpendings.add(aprilMonthlySpending);
			
			MonthlySpending mayMonthlySpending = new MonthlySpending();
			mayMonthlySpending.setMasterReport(cashOutReport);
			mayMonthlySpending.setMonth("May");
			mayMonthlySpending.setAmount(3500);
			monthlySpendings.add(mayMonthlySpending);
			
			MonthlySpending juneMonthlySpending = new MonthlySpending();
			juneMonthlySpending.setMasterReport(cashOutReport);
			juneMonthlySpending.setMonth("June");
			juneMonthlySpending.setAmount(2000);
			monthlySpendings.add(juneMonthlySpending);
			
			MonthlySpending julyMonthlySpending = new MonthlySpending();
			julyMonthlySpending.setMasterReport(cashOutReport);
			julyMonthlySpending.setMonth("July");
			julyMonthlySpending.setAmount(2200);
			monthlySpendings.add(julyMonthlySpending);
			
			MonthlySpending augustMonthlySpending = new MonthlySpending();
			augustMonthlySpending.setMasterReport(cashOutReport);
			augustMonthlySpending.setMonth("August");
			augustMonthlySpending.setAmount(1500);
			monthlySpendings.add(augustMonthlySpending);
			
			MonthlySpending septemberMonthlySpending = new MonthlySpending();
			septemberMonthlySpending.setMasterReport(cashOutReport);
			septemberMonthlySpending.setMonth("September");
			septemberMonthlySpending.setAmount(1800);
			monthlySpendings.add(septemberMonthlySpending);
			
			MonthlySpending octoberMonthlySpending = new MonthlySpending();
			octoberMonthlySpending.setMasterReport(cashOutReport);
			octoberMonthlySpending.setMonth("October");
			octoberMonthlySpending.setAmount(1750);
			monthlySpendings.add(octoberMonthlySpending);
			
			MonthlySpending novemberMonthlySpending = new MonthlySpending();
			novemberMonthlySpending.setMasterReport(cashOutReport);
			novemberMonthlySpending.setMonth("November");
			novemberMonthlySpending.setAmount(2850);
			monthlySpendings.add(novemberMonthlySpending);
			
			MonthlySpending decemberMonthlySpending = new MonthlySpending();
			decemberMonthlySpending.setMasterReport(cashOutReport);
			decemberMonthlySpending.setMonth("December");
			decemberMonthlySpending.setAmount(3890);	
			monthlySpendings.add(decemberMonthlySpending);
			
			cashOutReport.setMonthlySpendings(monthlySpendings);
			
			cashOutReportRepository.save(cashOutReport);
			
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
