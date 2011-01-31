package org.gwtcom.server.dao.gaeimpl;

import org.gwtcom.server.converter.gaeimpl.UserLoginConverter;
import org.gwtcom.server.dao.UserLoginDao;
import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.shared.UserLoginRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userLoginDao")
public class UserLoginDaoGaeImpl extends GenericDaoGaeImpl<UserLogin, String> implements UserLoginDao {

	@Autowired
	private UserLoginConverter _userLoginConverter;

	public UserLoginDaoGaeImpl() {
		super(UserLogin.class);
	}

	@Override
	public UserLoginRemote getUserLogin(String userLoginId) {
		return _userLoginConverter.convertDomainToRemote(retrieve(userLoginId));
	}

}
