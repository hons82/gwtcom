package org.gwtcom.server;

import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.shared.UserLoginRemote;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractUserAwareService extends AbstractDatabaseService {

	private AuthenticationServiceImpl _authenticationService;

	@Autowired
	public void setAuthenticationService(AuthenticationServiceImpl authenticationService) {
		_authenticationService = authenticationService;
	}

	public UserLogin getUserLogin() {
		return _authenticationService.getUserLogin();
	}

	public UserLoginRemote getUserLoginRemote() {
		return _authenticationService.getUserLoginRemote();
	}
}
