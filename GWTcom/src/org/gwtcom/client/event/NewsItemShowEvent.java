package org.gwtcom.client.event;

import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.event.shared.GwtEvent;

public class NewsItemShowEvent extends GwtEvent<INewsItemShowEvent> {
	public static Type<INewsItemShowEvent> TYPE = new Type<INewsItemShowEvent>();
	private NewsItemRemote _item;

	public NewsItemShowEvent(NewsItemRemote item) {
		_item = item;
	}

	@Override
	public Type<INewsItemShowEvent> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(INewsItemShowEvent handler) {
		handler.onNewsItemShow(this);
	}

	public NewsItemRemote getItem() {
		return _item;
	}

	public void setItem(NewsItemRemote item) {
		_item = item;
	}
}
