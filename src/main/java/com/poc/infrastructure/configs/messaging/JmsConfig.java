package com.poc.infrastructure.configs.messaging;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.poc.domain.messaging.consumers.CashOutReportPreparer;

@Configuration
public class JmsConfig {

	@Value("${jms.activemq.queue}")
    private String analyticsQueue;
	
	@Autowired
	private CashOutReportPreparer cashOutReportPreparer;
	
	/*
	 * ..
	 * 
	 * @Autowired
	 * private MessageListener1 messageListener1;
	 * 
	 * @Autowired
	 * private MessageListener2 messageListener2;
	 * 
	 * @Autowired
	 * private MessageListener3 messageListener3;
	 * 
	 */
	
	@Bean
	public CashOutReportPreparer cashOutReportPreparer() {		
		return new CashOutReportPreparer();
	}	
	
	/*
	 * ..
	 * 
	 * @Bean
	 * public MessageListener1 messageListener1() {		
	 * 	   return new MessageListener1();
	 * }
	 * 
	 * @Bean
	 * public MessageListener2 messageListener2() {		
	 * 	   return new MessageListener2();
	 * } 
	 * 
	 * @Bean
	 * public MessageListener3 messageListener3() {		
	 * 	   return new MessageListener3();
	 * } 
	 * 
	 * */
	
	@Bean
	public DefaultMessageListenerContainer defaultMessageListenerContainer(ConnectionFactory connectionFactory) {
		
		DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
		defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
		defaultMessageListenerContainer.setDestinationName(analyticsQueue);
		defaultMessageListenerContainer.setMessageListener(cashOutReportPreparer);
		/*
		 * ..
		 * defaultMessageListenerContainer.setMessageListener(`messageListener1`);
		 * defaultMessageListenerContainer.setMessageListener(`messageListener2`);
		 * defaultMessageListenerContainer.setMessageListener(`messageListener3`);
		 * 
		 * */
		
		return defaultMessageListenerContainer;
	}

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setDefaultDestinationName(analyticsQueue);
		return jmsTemplate;
	}
	
}
