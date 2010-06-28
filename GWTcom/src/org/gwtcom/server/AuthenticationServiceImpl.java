package org.gwtcom.server;

import java.util.ArrayList;
import java.util.Collection;

import org.gwtcom.client.service.AuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;

public class AuthenticationServiceImpl implements AuthenticationService {

	public boolean authenticate(String username, String password) {
		// creating an authenticated user token for demo
		// regardless of username and password values
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		User user = new User("xxx", "yyy", true, true, true, true, authorities);
		Authentication auth = new UsernamePasswordAuthenticationToken(user, password, authorities);
		SecurityContext sc = new SecurityContextImpl();
		sc.setAuthentication(auth);
		SecurityContextHolder.setContext(sc);

		return true;
	}

	public void logout() {
		SecurityContextHolder.clearContext();
	}

	@Override
	public boolean isLoggedIn() {
		System.out.println(">>>>> AuthenticationService.isLoggedIn -> " + SecurityContextHolder.getContext().getAuthentication());
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			return true;
		}
		return false;
	}
}
