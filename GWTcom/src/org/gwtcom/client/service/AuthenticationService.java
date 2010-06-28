package org.gwtcom.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtcom/authenticationService")
public interface AuthenticationService extends RemoteService {

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
