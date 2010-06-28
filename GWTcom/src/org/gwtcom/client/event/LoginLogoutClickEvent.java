package org.gwtcom.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LoginLogoutClickEvent extends GwtEvent<ILoginLogoutClickEvent> {

		public static final Type<ILoginLogoutClickEvent> TYPE = new Type<ILoginLogoutClickEvent>();
		private boolean _loggedIn;

	    public LoginLogoutClickEvent(boolean loggedIn) {
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

		public void setLoggedIn(boolean loggedIn) {
			_loggedIn = loggedIn;
		}

		public boolean isLoggedIn() {
			return _loggedIn;
		}

}
