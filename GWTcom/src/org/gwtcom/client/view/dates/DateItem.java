package org.gwtcom.client.view.dates;

import org.gwtcom.shared.DateItemRemote;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public interface DateItem {
	public void setData(DateItemRemote item);

	public HasClickHandlers getList();

	public Widget asWidget();

	public void startProcessing();

	public void stopProcessing();

	public Button getBackButton();

	public void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}
}
