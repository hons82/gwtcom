package org.gwtcom.server.dao;

import org.gwtcom.server.domain.Authority;
import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.shared.UserLoginRemote;

public interface UserLoginDao extends GenericDao<UserLogin, String>{

	public UserLoginRemote getUserLogin(String userLoginId);
	
	public UserLogin getUserLoginByProfileId(String userProfileId);

	public UserLogin getUserLoginByName(String name);

	public void addRoletoUser(String name, Authority auth);

}
