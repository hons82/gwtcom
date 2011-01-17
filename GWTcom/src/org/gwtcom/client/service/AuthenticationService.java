package org.gwtcom.client.service;

import org.gwtcom.shared.UserLoginRemote;

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
	public UserLoginRemote authenticate(String username, String password);

	/**
	 * Terminates a user's security session.
	 */
	public void logout();

	public UserLoginRemote isLoggedIn();

}
