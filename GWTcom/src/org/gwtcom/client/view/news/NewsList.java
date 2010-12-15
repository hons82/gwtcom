package org.gwtcom.client.view.news;

import java.util.List;

import org.gwtcom.client.view.navigation.NavBar;
import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;

public interface NewsList extends IsWidget {

	public void setData(List<NewsItemRemote> item);

	public HasClickHandlers getList();

	public void startProcessing();

	public void stopProcessing();

	public FlexTable getNewsListTable();

	public int getClickedRow(ClickEvent event);

	public NavBar getNavBar();

	public void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}
	
	interface SelectionStyle extends CssResource {
		String selectedRow();
	}

	void newer(List<NewsItemRemote> data);

	void older(List<NewsItemRemote> data);
}