package org.gwtcom.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * The client side stub for the RPC service.
 */
public interface GreetingService extends RemoteService {
	
	public static final String SERVICE_URI = "greetingService";

	public static class Util {
		public static GreetingServiceAsync getInstance() {
			GreetingServiceAsync instance = (GreetingServiceAsync) GWT
					.create(GreetingService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICE_URI);
			return instance;
		}
	}
	
	String greetServer(String name) throws IllegalArgumentException;
}
