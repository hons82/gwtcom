package org.gwtcom.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class DateListShowEvent extends GwtEvent<IDateListShowEvent> {
	public static Type<IDateListShowEvent> TYPE = new Type<IDateListShowEvent>();

	public DateListShowEvent() {
	}

	@Override
	public Type<IDateListShowEvent> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(IDateListShowEvent handler) {
		handler.onDateListShow(this);
	}

}
