package org.gwtcom.client.panel.navigation;

import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.user.client.ui.Composite;

public abstract class AbstractStackPanelInlay extends Composite {

	public abstract void setLoggedIn(UserLoginRemote loggedIn);
	
}
