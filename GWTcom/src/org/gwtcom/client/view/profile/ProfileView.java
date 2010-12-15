package org.gwtcom.client.view.profile;

import org.gwtcom.shared.UserProfileRemote;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Widget;

public interface ProfileView {

	public abstract void setData(UserProfileRemote item);

	public abstract HasClickHandlers getList();

	public abstract Widget asWidget();

	public abstract void startProcessing();

	public abstract void stopProcessing();
	public void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}
}