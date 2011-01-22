package org.gwtcom.client.view.profile;

import java.util.List;

import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Widget;

public interface ProfileView {
	
	public abstract Long getLoginId();
	
	public abstract Long getProfileId();

	public abstract void setProfileData(UserProfileRemote item);

	public abstract HasClickHandlers getList();

	public abstract Widget asWidget();

	public abstract void setProfileWallData(List<WallEntryRemote> result);

	public void addProfileWallEntry(WallEntryRemote entry);

	public abstract void addWallPostClickHandler(ClickHandler clickHandler);

	public abstract String getWallPostInputContent();

	public void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}
}