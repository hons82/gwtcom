package org.gwtcom.server;

import org.gwtcom.client.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
public class GreetingServiceServlet extends DependencyInjectionRemoteServiceServlet implements GreetingService {

	@Autowired
	GreetingService greetingService;

	public String greetServer(String name) throws IllegalArgumentException {
			return greetingService.greetServer(name);
	}

}
