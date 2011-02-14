package org.gwtcom.client.view.news.change;

import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.IsWidget;

public interface NewsChange extends IsWidget {

	public HasClickHandlers getList();

	public void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}

	public CellList<NewsItemRemote> getNewsList();
	
	public void cancelButtonClickHandler(ClickHandler clickHandler);

	public void saveButtonClickHandler(ClickHandler clickHandler);

	public NewsItemRemote getSelectedItem();

	public String getContentasHTML();

}