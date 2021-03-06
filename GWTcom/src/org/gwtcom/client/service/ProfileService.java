package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.FriendEntryRemote;
import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service/profileService")
public interface ProfileService extends RemoteService {

	public UserProfileRemote getUserProfile(String userProfileId) throws ServiceSecurityException;

	public boolean updateUserProfile(UserProfileRemote profile);

	public UserProfileRemote getUserProfileByUserLoginId(String userLoginId);

	public List<WallEntryRemote> getPublicWallEntries(String userLoginId) throws ServiceSecurityException;

	public WallEntryRemote addWallPost(String userLoginId, String content);

	public List<FriendEntryRemote> getPublicFriendEntries(String userLoginId);

}
