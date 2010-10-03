package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.UserLoginRemote;
import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProfileServiceAsync {

	public void getProfile(Long item, AsyncCallback<UserProfileRemote> callback);

	public void getUserLogin(String name, AsyncCallback<UserLoginRemote> callback);

	public void getPublicWallEntries(Long id, AsyncCallback<List<WallEntryRemote>> callback);
}
