package org.gwtcom.client.view.profile;

import java.util.List;

import org.gwtcom.client.view.bundles.ProfileClientBundle;
import org.gwtcom.client.view.profile.friends.FriendsPanel;
import org.gwtcom.client.view.profile.wall.WallPanel;
import org.gwtcom.shared.FriendEntryRemote;
import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

/**
 * A composite for displaying the details of a Profile message.
 */
public class ProfileViewImpl extends ResizeComposite implements ProfileView {

	public static final String PROFILEIMAGESERVLET_URL = "/gwtcom/profileImage?id=";

	interface Binder extends UiBinder<Widget, ProfileViewImpl> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	private static final ProfileClientBundle profileBundle = GWT.create(ProfileClientBundle.class);

	@UiField
	Hidden profileId;
	@UiField
	Label name;
	@UiField
	Label email;
	@UiField
	Label gender;
	@UiField
	WallPanel wall;
	@UiField
	FriendsPanel friends;
	@UiField
	HTMLPanel imagePanel;

	private Presenter _presenter;

	public ProfileViewImpl() {
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void setProfileData(UserProfileRemote item) {
		System.out.println(">>>>> ProfileItem.setData()");
		if (item != null) {
			profileId.setValue(String.valueOf(item.getId()));
			name.setText(item.getFirstname() + " " + item.getLastname());
			email.setText(item.getEmail());
			gender.setText((item.getGender() == 0 ? "male" : "female"));

			Image image = new Image(profileBundle.noprofile());
			// if (item.getProfileImage() != null && item.getProfileImage().getImage() != null) {
			// // add the real image to the panel
			// StringBuilder sb = new StringBuilder();
			// sb.append("data:image/gif;base64,");
			// sb.append(item.getProfileImage().getImage());
			//
			// image.setUrl(sb.toString());
			// }
			image.setUrl(PROFILEIMAGESERVLET_URL + item.getProfileImage().getId());
			image.setSize("175px", "200px");
			imagePanel.clear();
			imagePanel.add(image, "imageDiv");
		} else {
			// TODO
		}
	}

	@Override
	public String getProfileId() {
		return profileId.getValue();
	}

	@Override
	public void setProfileWallData(List<WallEntryRemote> result) {
		wall.clearWall();
		for (WallEntryRemote entry : result) {
			wall.addWallItem(entry);
		}
	}

	@Override
	public void addProfileWallEntry(WallEntryRemote entry) {
		wall.addWallItem(entry);
	}

	@Override
	public void addWallPostClickHandler(ClickHandler clickHandler) {
		wall.addWallPostClickHandler(clickHandler);
	}

	@Override
	public String getWallPostInputContent() {
		return wall.getInputContent();
	}

	@Override
	public void setProfileFriendsData(List<FriendEntryRemote> result) {
		friends.clearFriends();
		for (FriendEntryRemote entry : result) {
			friends.addFriendItem(entry);
		}
	}

	@Override
	public void addProfileFriendEntry(FriendEntryRemote entry) {
		friends.addFriendItem(entry);
	}

	@Override
	public void addFriendsClickHandler(ClickHandler clickHandler) {
		friends.addFriendsClickHandler(clickHandler);
	}
	
	@Override
	public HasClickHandlers getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		_presenter = presenter;
	}

}