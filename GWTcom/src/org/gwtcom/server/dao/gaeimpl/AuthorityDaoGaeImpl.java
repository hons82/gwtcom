package org.gwtcom.server.dao.gaeimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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

import com.google.appengine.api.datastore.Key;
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
		user = _userLoginDao.retrieve(KeyFactory.keyToString(user.getId()));
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		for (Iterator<Key> iterator = user.getAuthorities().iterator(); iterator.hasNext();) {
			Key k = iterator.next();
			Authority a = retrieve(KeyFactory.keyToString(k));
			authList.add(new GrantedAuthorityImpl(a.getAuthname()));
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
