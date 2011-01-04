package org.gwtcom.client.view.news;

import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Widget;

public interface NewsItem {

	public void setData(NewsItemRemote item);

	public HasClickHandlers getList();

	public Widget asWidget();

	public void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}
}