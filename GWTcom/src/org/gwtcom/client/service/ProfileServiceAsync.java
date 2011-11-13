package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.FriendEntryRemote;
import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProfileServiceAsync {

	public void getUserProfile(String userProfileId, AsyncCallback<UserProfileRemote> callback);
	
	public void updateUserProfile(UserProfileRemote profile, AsyncCallback<Boolean> callback);

	public void getUserProfileByUserLoginId(String userLoginId, AsyncCallback<UserProfileRemote> callback);

	public void getPublicWallEntries(String userLoginId, AsyncCallback<List<WallEntryRemote>> callback);

	public void addWallPost(String userLoginId, String content, AsyncCallback<WallEntryRemote> asyncCallback);

	public void getPublicFriendEntries(String userLoginId, AsyncCallback<List<FriendEntryRemote>> asyncCallback);
}
