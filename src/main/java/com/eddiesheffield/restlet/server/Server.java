package com.eddiesheffield.restlet.server;

import org.restlet.Component;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {
	public static void main(String[] args) throws BeansException, Exception {
		// load the Spring application context
		ApplicationContext springContext = new ClassPathXmlApplicationContext(
			new String[] { "applicationContext-resources.xml", "applicationContext-router.xml", "applicationContext-server.xml" });

		// obtain the Restlet component from the Spring context and start it
		((Component) springContext.getBean("top")).start();
	}

}
