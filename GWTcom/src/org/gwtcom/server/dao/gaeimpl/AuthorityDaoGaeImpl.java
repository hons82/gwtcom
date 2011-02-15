package org.gwtcom.server.dao.gaeimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.gwtcom.server.dao.AuthorityDao;
import org.gwtcom.server.dao.UserLoginDao;
import org.gwtcom.server.domain.Authority;
import org.gwtcom.server.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.appengine.api.datastore.KeyFactory;

@Repository("authorityDao")
public class AuthorityDaoGaeImpl extends GenericDaoGaeImpl<Authority, String> implements AuthorityDao {
	
	@Autowired
	public UserLoginDao _userLoginDao;

	public AuthorityDaoGaeImpl(){
		super(Authority.class);
	}
	
	@Override
	public Collection<GrantedAuthority> getGrantedAuthorities(UserLogin user) {
		List<String> userauth = _userLoginDao.getAuthoritiesByUserLoginId(KeyFactory.keyToString(user.getId()));
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		for (String key : userauth) {
			Authority authority = retrieve(key);
			authList.add(new GrantedAuthorityImpl(authority.getAuthname()));
		}
		return authList;
	}

	@Override
	@Transactional(readOnly = true)
	public Authority getAuthoritybyName(final String name) {
		Number count = (Number) _entityManager
				.createQuery(
						"SELECT count(distinct _authname) FROM " + Authority.class.getName() + " WHERE _authname =\"" + name
								+ "\"").getSingleResult();
		if (count.intValue() == 1) {
			@SuppressWarnings("unchecked")
			List<Authority> resultList = _entityManager.createQuery(
					"SELECT FROM " + Authority.class.getName() + " WHERE _authname =\"" + name + "\"").getResultList();
			return resultList.get(0);
		}
		return null;
	}
}
