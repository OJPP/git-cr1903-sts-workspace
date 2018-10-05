package com.apress.prospring3.ch16.jms;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.ch16.jms.sender.MessageSender;

public class JmsSenderSample {

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:jms-sender-app-context.xml");
		ctx.refresh();

		// Send message
		MessageSender messageSender = ctx.getBean("messageSender", MessageSender.class);

		messageSender.sendMessage("Clarence testing JMS message");

		ctx.close();
	}

}
