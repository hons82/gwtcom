package org.gwtcom.client.service;

import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProfileServiceAsync {

	public void getProfile(Long item, AsyncCallback<UserProfileRemote> callback);

	public void getUserData(String name, AsyncCallback<UserLoginRemote> callback);
}
