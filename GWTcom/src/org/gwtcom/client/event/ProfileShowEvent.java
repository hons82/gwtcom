package org.gwtcom.client.event;

import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.event.shared.GwtEvent;

public class ProfileShowEvent extends GwtEvent<IProfileShowEvent> {
	public static Type<IProfileShowEvent> TYPE = new Type<IProfileShowEvent>();
	private UserLoginRemote _item;
	
	public ProfileShowEvent(UserLoginRemote item) {
		_item = item;
	}

	@Override
	public Type<IProfileShowEvent> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(IProfileShowEvent handler) {
		handler.onProfileShow(this);
	}

	public UserLoginRemote getItem() {
		return _item;
	}

	public void setItem(UserLoginRemote item) {
		_item = item;
	}
}
