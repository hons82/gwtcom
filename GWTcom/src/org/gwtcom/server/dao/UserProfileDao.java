package org.gwtcom.server.dao;

import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.UserProfileRemote;

public interface UserProfileDao extends GenericDao<UserProfile, String>{

	public UserProfileRemote getUserProfile(String userProfileId);

	public boolean updateUserProfile(UserProfileRemote profile);

	public UserProfileRemote getUserProfile(UserLogin userLogin);
	
	public UserProfile getProfileWithWall(String userProfileId);

}
