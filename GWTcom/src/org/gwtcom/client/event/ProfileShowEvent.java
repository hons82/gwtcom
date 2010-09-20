package org.gwtcom.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ProfileShowEvent extends GwtEvent<IProfileShowEvent> {
	public static Type<IProfileShowEvent> TYPE = new Type<IProfileShowEvent>();

	public ProfileShowEvent() {
	}

	@Override
	public Type<IProfileShowEvent> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(IProfileShowEvent handler) {
		handler.onProfileShow(this);
	}

}
