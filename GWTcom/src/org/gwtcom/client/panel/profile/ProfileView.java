package org.gwtcom.client.panel.profile;

import org.gwtcom.client.panel.profile.wall.WallPanel;
import org.gwtcom.client.presenter.ProfileViewPresenter;
import org.gwtcom.shared.UserProfileRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

/**
 * A composite for displaying the details of a Profile message.
 */
public class ProfileView extends ResizeComposite implements ProfileViewPresenter.Display {

	interface Binder extends UiBinder<Widget, ProfileView> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	Label name;
	@UiField
	Label email;
	@UiField
	Label gender;
	@UiField
	WallPanel wall;

	public ProfileView() {
		initWidget(binder.createAndBindUi(this));
		
	}

	@Override
	public void setData(UserProfileRemote item) {
		System.out.println(">>>>> ProfileItem.setData()");
		if (item != null) {
			name.setText(item.getName());
			email.setText(item.getEmail());
			gender.setText((item.getGender()==0?"male":"female"));
		}else{
		}
		//TEST
		for (int i = 0; i < 10; i++) {
			wall.addWallItem(null);
		}
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
	public void startProcessing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopProcessing() {
		// TODO Auto-generated method stub

	}

}