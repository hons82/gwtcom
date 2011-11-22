package org.gwtcom.client.view.people;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface PeopleView extends IsWidget {

	public void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}
}