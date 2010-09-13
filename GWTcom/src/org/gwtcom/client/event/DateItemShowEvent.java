package org.gwtcom.client.event;

import org.gwtcom.shared.DateItemRemote;

import com.google.gwt.event.shared.GwtEvent;

public class DateItemShowEvent extends GwtEvent<IDateItemShowEvent> {
	public static Type<IDateItemShowEvent> TYPE = new Type<IDateItemShowEvent>();
	private DateItemRemote _item;

	public DateItemShowEvent(DateItemRemote item) {
		_item = item;
	}

	@Override
	public Type<IDateItemShowEvent> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(IDateItemShowEvent handler) {
		handler.onDateItemShow(this);
	}

	public DateItemRemote getItem() {
		return _item;
	}

	public void setItem(DateItemRemote item) {
		_item = item;
	}
}
