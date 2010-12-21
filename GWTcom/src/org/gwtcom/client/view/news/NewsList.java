package org.gwtcom.client.view.news;

import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.IsWidget;

public interface NewsList extends IsWidget {

	public HasClickHandlers getList();

	public CellList<NewsItemRemote> getNewsListTable();

	public void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}
	
}