package org.gwtcom.server;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class SimpleAuthenticationProvider implements AuthenticationProvider {

	public Authentication authenticate(Authentication arg0)
			throws AuthenticationException {
		throw new IllegalStateException("just for the sake of namespace");
	}

	public boolean supports(Class<? extends Object> arg0) {
		throw new IllegalStateException("just for the sake of namespace");
	}

}
