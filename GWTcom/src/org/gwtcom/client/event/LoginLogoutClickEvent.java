package org.gwtcom.client.event;

import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.event.shared.GwtEvent;

public class LoginLogoutClickEvent extends GwtEvent<ILoginLogoutClickEvent> {

	public static final Type<ILoginLogoutClickEvent> TYPE = new Type<ILoginLogoutClickEvent>();
	private UserLoginRemote _userLoginRemote;

	public LoginLogoutClickEvent(UserLoginRemote loggedIn) {
		super();
		setLoggedIn(loggedIn);
	}

	@Override
	protected void dispatch(ILoginLogoutClickEvent handler) {
		handler.onLoginLogoutClick(this);
	}

	@Override
	public Type<ILoginLogoutClickEvent> getAssociatedType() {
		return TYPE;
	}

	public void setLoggedIn(UserLoginRemote userLoginRemote) {
		_userLoginRemote = userLoginRemote;
	}

	public UserLoginRemote isLoggedIn() {
		return _userLoginRemote;
	}

}
