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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

	public boolean authenticate(String username, String password) {
		System.out.println(">>>>> Authenticate called");
		// creating an authenticated user token for demo
		// regardless of username and password values
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		User user = new User("root", "root", true, true, true, true, authorities);
		Authentication auth = new UsernamePasswordAuthenticationToken(user, password, authorities);
		SecurityContext sc = new SecurityContextImpl();

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		attr.getRequest().getSession().setAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY, username);

		sc.setAuthentication(auth);
		SecurityContextHolder.setContext(sc);

		return true;
	}

	public void logout() {
		System.out.println(">>>>> Logout called");
		SecurityContextHolder.clearContext();
	}

	@Override
	public boolean isLoggedIn() {
		System.out.println(">>>>> AuthenticationService.isLoggedIn -> " + SecurityContextHolder.getContext().getAuthentication());
		// TODO: That's maybe not enough of security 
		if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			return true;
		}
		return false;
	}
}
