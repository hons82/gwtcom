package org.gwtcom.server.dao;

import java.util.List;

import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.FriendEntryRemote;
import org.gwtcom.shared.UserProfileRemote;

public interface UserProfileDao extends GenericDao<UserProfile, String>{

	public UserProfileRemote getUserProfile(String userProfileId);

	public boolean updateUserProfile(UserProfileRemote profile);

	public UserProfileRemote getUserProfile(UserLogin userLogin);
	
	public UserProfile getProfileWithWall(String userProfileId);

	public void addFriendtoUser(String userProfileId, String friendID);

	// this will go into a new DAO 
	public List<FriendEntryRemote> getFriendsOfUser(UserLogin userLogin);
	
	public List<FriendEntryRemote> getPeople(String pattern);
}
