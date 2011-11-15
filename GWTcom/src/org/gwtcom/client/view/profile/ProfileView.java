package org.gwtcom.client.view.profile;

import java.util.List;

import org.gwtcom.shared.FriendEntryRemote;
import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface ProfileView extends IsWidget{
	
	public String getProfileId();

	public void setProfileData(UserProfileRemote item);

	public HasClickHandlers getList();

	public void setProfileWallData(List<WallEntryRemote> result);

	public void addProfileWallEntry(WallEntryRemote entry);

	public void addWallPostClickHandler(ClickHandler clickHandler);

	public String getWallPostInputContent();
	
	void setProfileFriendsData(List<FriendEntryRemote> result);

	public void addProfileFriendEntry(FriendEntryRemote entry);

	public void addFriendsClickHandler(ClickHandler clickHandler);
	
	public void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}
}