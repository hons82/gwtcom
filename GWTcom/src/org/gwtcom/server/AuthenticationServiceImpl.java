package org.gwtcom.server;

import java.util.List;

import javax.persistence.Query;

import org.gwtcom.client.service.AuthenticationService;
import org.gwtcom.server.converter.gaeimpl.UserLoginConverter;
import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.server.security.CustomUserDetailService;
import org.gwtcom.shared.UserLoginRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service("authenticationService")
public class AuthenticationServiceImpl extends AbstractDatabaseService implements AuthenticationService {

	private UserLoginConverter _userLoginConverter;
	private CustomUserDetailService _customUserDetailsService;

	@Autowired
	public void setuserLoginConverter(UserLoginConverter userLoginConverter) {
		_userLoginConverter = userLoginConverter;
	}

	@Autowired
	public void setCustomUserDetailService(CustomUserDetailService customUserDetailsService) {
		_customUserDetailsService = customUserDetailsService;
	}

	@Override
	public UserLoginRemote authenticate(String username, String password) {
		System.out.println(">>>>> Authenticate called");
		UserDetails userdetail = null;
		try {
			userdetail = _customUserDetailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException e) {
			return null;
		} catch (DataAccessException e) {
			return null;
		}

		// TODO: Check for SQLInjection

		if (!_customUserDetailsService.encodePassword(password).equals(userdetail.getPassword())) {
			return null;
		}

		Authentication auth = new UsernamePasswordAuthenticationToken(userdetail.getUsername(), userdetail.getPassword(),
				userdetail.getAuthorities());
		SecurityContext sc = new SecurityContextImpl();

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		attr.getRequest()
				.getSession()
				.setAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY,
						userdetail.getUsername());

		sc.setAuthentication(auth);
		SecurityContextHolder.setContext(sc);

		return getUserLoginRemoteByName(userdetail.getUsername());
	}

	@Override
	public void logout() {
		System.out.println(">>>>> Logout called");
		SecurityContextHolder.clearContext();
	}

	@Override
	public UserLoginRemote isLoggedIn() {
		UserLoginRemote userLoginRemote = getUserLoginRemote();
		return (userLoginRemote != null ? userLoginRemote : null);
	}

	public UserLoginRemote getUserLoginRemote() {
		UserLogin userLogin = getUserLogin();
		return (userLogin != null ? _userLoginConverter.convertDomainToRemote(userLogin) : null);
	}

	public UserLogin getUserLogin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(">>>>> AuthenticationService.isLoggedIn -> " + authentication);
		// TODO: That's maybe not enough of security
		if (authentication != null && authentication.isAuthenticated()
				&& !authentication.getPrincipal().equals("anonymousUser")) {
			return getUserLoginByName(authentication.getPrincipal().toString());
		}
		return null;
	}

	private UserLoginRemote getUserLoginRemoteByName(String name) {
		UserLogin userLoginByName = getUserLoginByName(name);
		return (userLoginByName != null ? _userLoginConverter.convertDomainToRemote(userLoginByName) : null);
	}

	@SuppressWarnings("unchecked")
	private UserLogin getUserLoginByName(String name) {
		Query query = getEntityManager().createQuery(
				"SELECT FROM " + UserLogin.class.getName() + " WHERE _name = :nameParam");
		query.setParameter("nameParam", name);
		List<UserLogin> udl = query.getResultList();
		if (udl != null && udl.size() == 1) {
			UserLogin ud = udl.get(0);
			if (ud.getUserprofile() != null) {
				return ud;
			}
		}
		System.out.println("User has no profile attached!");
		return null;
	}

}
