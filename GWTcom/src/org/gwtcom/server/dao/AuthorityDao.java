package org.gwtcom.server.dao;

import java.util.Collection;

import org.gwtcom.server.domain.Authority;
import org.gwtcom.server.domain.UserLogin;
import org.springframework.security.core.GrantedAuthority;

public interface AuthorityDao extends GenericDao<Authority, String>{

	public Collection<GrantedAuthority> getGrantedAuthorities(UserLogin user);

	public Authority getAuthoritybyName(String name);

}
