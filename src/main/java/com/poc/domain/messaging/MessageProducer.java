package com.poc.domain.messaging;

import com.poc.web.models.CashOutReportRequest;

public interface MessageProducer {

    void pushMessage(CashOutReportRequest cashOutReportRequest);
    
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
