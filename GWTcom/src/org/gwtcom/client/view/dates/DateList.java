package org.gwtcom.client.view.dates;

import java.util.List;

import org.gwtcom.client.view.navigation.NavBar;
import org.gwtcom.shared.DateItemRemote;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public interface DateList {
	public void setData(List<DateItemRemote> item);

	public HasClickHandlers getList();

	public Widget asWidget();

	public void startProcessing();

	public void stopProcessing();

	public FlexTable getDateListTable();

	public int getClickedRow(ClickEvent event);

	public NavBar getNavBar();

	public void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}

	interface SelectionStyle extends CssResource {
		String selectedRow();
	}

	void newer(List<DateItemRemote> data);

	void older(List<DateItemRemote> data);

}
