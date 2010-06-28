package org.gwtcom.server;

import org.gwtcom.client.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
public class AuthenticationServiceServlet extends DependencyInjectionRemoteServiceServlet implements AuthenticationService {

	@Autowired
	AuthenticationService authenticationService;

	public boolean authenticate(String username, String password) {
		return authenticationService.authenticate(username, password);
	}

	public void logout() {
		authenticationService.logout();
	}

	@Override
	public boolean isLoggedIn() {
		return authenticationService.isLoggedIn();
	}

}
