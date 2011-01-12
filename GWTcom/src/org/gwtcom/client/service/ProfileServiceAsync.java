package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProfileServiceAsync {

	public void getUserProfile(Long userProfileId, AsyncCallback<UserProfileRemote> callback);
	
	public void getUserProfileByUserLoginId(Long userLoginId, AsyncCallback<UserProfileRemote> callback);

	public void getPublicWallEntries(Long userProfileId, AsyncCallback<List<WallEntryRemote>> callback);

	public void addWallPost(Long userProfileId, String content, AsyncCallback<WallEntryRemote> asyncCallback);
}
