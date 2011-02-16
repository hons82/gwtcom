package org.gwtcom.server.dao.gaeimpl;

import java.util.ArrayList;
import java.util.List;

import org.gwtcom.server.converter.gaeimpl.UserLoginConverter;
import org.gwtcom.server.dao.UserLoginDao;
import org.gwtcom.server.dao.UserProfileDao;
import org.gwtcom.server.domain.Authority;
import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.UserLoginRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Repository("userLoginDao")
public class UserLoginDaoGaeImpl extends GenericDaoGaeImpl<UserLogin, String> implements UserLoginDao {

	@Autowired
	private UserLoginConverter _userLoginConverter;
	@Autowired
	private UserProfileDao _userProfileDao;

	public UserLoginDaoGaeImpl() {
		super(UserLogin.class);
	}

	@Override
	public UserLoginRemote getUserLogin(String userLoginId) {
		return _userLoginConverter.convertDomainToRemote(retrieve(userLoginId));
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public UserLogin getUserLoginByName(String name) {
		Number count = (Number) _entityManager.createQuery(
				"SELECT count(distinct _id) FROM " + UserLogin.class.getName() + " WHERE _name =\"" + name + "\"")
				.getSingleResult();
		if (count.intValue() == 1) {
			List<UserLogin> resultList = _entityManager.createQuery(
					"SELECT FROM " + UserLogin.class.getName() + " WHERE _name =\"" + name + "\"").getResultList();
			return resultList.get(0);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void addRoletoUser(String name, Authority auth) {
		UserLogin user = getUserLoginByName(name);
		if (user != null) {
			user.getAuthorities().add(auth.getId());
			saveOrUpdate(user);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserLogin getUserLoginByProfileId(String userProfileId) {
		UserProfile userProfile = _userProfileDao.retrieve(userProfileId);
		return (userProfile != null ? userProfile.getLogin() : null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getAuthoritiesByUserLoginId(String userLoginId) {
		UserLogin userLogin = retrieve(userLoginId);
		List<String> ret = new ArrayList<String>();
		for (Key key : userLogin.getAuthorities()) {
			ret.add(KeyFactory.keyToString(key));
		}
		return ret;
	}
	
	@Override
	public UserProfile getUserProfile(UserLoginRemote loggedInUserRemote) {
		// gather all information about the author
		UserLogin loggedInUser = retrieve(loggedInUserRemote.getId());
		UserProfile loggedInUserProfile = loggedInUser != null ? loggedInUser.getUserprofile() : null;
		return loggedInUserProfile;
	}

}
