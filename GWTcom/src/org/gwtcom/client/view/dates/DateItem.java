package org.gwtcom.client.view.dates;

import org.gwtcom.shared.DateItemRemote;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface DateItem extends IsWidget{
	public void setData(DateItemRemote item);

	public HasClickHandlers getList();

	public void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}
}
