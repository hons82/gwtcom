package org.gwtcom.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class NewsListShowEvent extends GwtEvent<INewsListShowEvent> {
	public static Type<INewsListShowEvent> TYPE = new Type<INewsListShowEvent>();

	public NewsListShowEvent() {
	}

	@Override
	public Type<INewsListShowEvent> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(INewsListShowEvent handler) {
		handler.onNewsListShow(this);
	}

}
