package org.gwtcom.client.view.profile;

import java.util.List;

import org.gwtcom.client.view.bundles.ProfileClientBundle;
import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

/**
 * A composite for displaying the details of a Profile message.
 */
public class ProfileChangeViewImpl extends ResizeComposite implements ProfileView {

	interface Binder extends UiBinder<Widget, ProfileChangeViewImpl> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	private static final ProfileClientBundle profileBundle = GWT.create(ProfileClientBundle.class);

	private Presenter _presenter;

	public ProfileChangeViewImpl() {
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void setProfileData(UserProfileRemote item) {
		System.out.println(">>>>> ProfileItem.setData()");
		if (item != null) {
//			name.setText(item.getName());
//			email.setText(item.getEmail());
//			gender.setText((item.getGender() == 0 ? "male" : "female"));
//
//			Image image = new Image(profileBundle.noprofile());
//			if (item.getProfileImage() != null && item.getProfileImage().getImage() != null) {
//				// add the real image to the panel
//				StringBuilder sb = new StringBuilder();
//				sb.append("data:image/gif;base64,");
//				sb.append(item.getProfileImage().getImage());
//
//				image.setUrl(sb.toString());
//			}
//			image.setSize("175px", "200px");
//			imagePanel.clear();
//			imagePanel.add(image, "imageDiv");
		} else {
			// TODO
		}
	}

	@Override
	public void setProfileWallData(List<WallEntryRemote> result) {
//		wall.clearWall();
//		for (WallEntryRemote entry : result) {
//			wall.addWallItem(entry);
//		}
	}

	@Override
	public void addProfileWallEntry(WallEntryRemote entry) {
//		wall.addWallItem(entry);
	}

	@Override
	public void addWallPostClickHandler(ClickHandler clickHandler) {
//		wall.addWallPostClickHandler(clickHandler);
	}

	@Override
	public String getWallPostInputContent() {
//		return wall.getInputContent();
		return null;
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