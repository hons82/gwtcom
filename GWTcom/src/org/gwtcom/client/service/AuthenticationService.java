package org.gwtcom.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface AuthenticationService extends RemoteService {

	public static final String SERVICE_URI = "authenticationService";

	public static class Util {
		public static AuthenticationServiceAsync getInstance() {
			AuthenticationServiceAsync instance = (AuthenticationServiceAsync) GWT
					.create(AuthenticationService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICE_URI);
			return instance;
		}
	}
	
	/**
	 * Authenticates user.
	 * 
	 * @param username
	 * @param password
	 * @return whether authentication is successful
	 */
	public boolean authenticate(String username, String password);

	/**
	 * Terminates a user's security session.
	 */
	public void logout();
	
	public boolean isLoggedIn();

}
