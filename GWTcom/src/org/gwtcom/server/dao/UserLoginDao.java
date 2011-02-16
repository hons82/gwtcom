package org.gwtcom.server.dao;

import java.util.List;

import org.gwtcom.server.domain.Authority;
import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.UserLoginRemote;

public interface UserLoginDao extends GenericDao<UserLogin, String> {

	public UserLoginRemote getUserLogin(String userLoginId);

	public UserLogin getUserLoginByProfileId(String userProfileId);

	public UserLogin getUserLoginByName(String name);

	public void addRoletoUser(String name, Authority auth);

	public List<String> getAuthoritiesByUserLoginId(String userLoginId);

	public UserProfile getUserProfile(UserLoginRemote loggedInUserRemote);

}
