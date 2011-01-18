package org.gwtcom.client.view.profile;

import org.gwtcom.shared.UserProfileRemote;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Widget;

public interface ProfileChangeView {

	public abstract void setProfileData(UserProfileRemote item);

	public abstract HasClickHandlers getList();

	public abstract Widget asWidget();
	
	public void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}
}