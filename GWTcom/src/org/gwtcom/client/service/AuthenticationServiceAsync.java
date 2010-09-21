package org.gwtcom.client.service;

import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthenticationServiceAsync {

	public void authenticate(String username, String password,
			AsyncCallback<UserLoginRemote> callback);

	public void logout(AsyncCallback<Void> callback);

	public void isLoggedIn(AsyncCallback<UserLoginRemote> asyncCallback);

}
