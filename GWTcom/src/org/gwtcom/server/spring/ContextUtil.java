package org.gwtcom.server.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextUtil {
	
	private static ApplicationContext context = null;

	private ContextUtil() {
		super();
	}

	public static ApplicationContext getContext() {
		// Load up our Application context

		if (context == null) {
			// TODO: change before deploying
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
		}

		return context;
	}
}
