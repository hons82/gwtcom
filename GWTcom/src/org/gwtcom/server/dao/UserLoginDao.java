package org.gwtcom.server.dao;

import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.shared.UserLoginRemote;

public interface UserLoginDao extends GenericDao<UserLogin, String>{

	UserLoginRemote getUserLogin(String userLoginId);

}
