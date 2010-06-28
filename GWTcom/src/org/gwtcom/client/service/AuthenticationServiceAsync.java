package org.gwtcom.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthenticationServiceAsync {

	public void authenticate(String username, String password,
			AsyncCallback<Boolean> callback);

	public void logout(AsyncCallback<Void> callback);

	void isLoggedIn(AsyncCallback<Boolean> callback);

}
