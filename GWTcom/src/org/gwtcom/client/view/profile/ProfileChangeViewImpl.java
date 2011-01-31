package org.gwtcom.client.view.profile;

import org.gwtcom.client.view.bundles.ProfileClientBundle;
import org.gwtcom.shared.UserProfileRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A composite for displaying the details of a Profile message.
 */
public class ProfileChangeViewImpl extends ResizeComposite implements ProfileChangeView {

	interface Binder extends UiBinder<Widget, ProfileChangeViewImpl> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	private static final ProfileClientBundle profileBundle = GWT.create(ProfileClientBundle.class);

	@UiField
	Hidden loginId;
	@UiField
	Hidden profileId;
	@UiField
	TextBox name;
	@UiField
	TextBox surname;
	@UiField
	TextBox email;
	@UiField
	RadioButton gender_male;
	@UiField
	RadioButton gender_female;
	@UiField
	PushButton saveBtn;
	@UiField
	PushButton cancelBtn;

	private Presenter _presenter;

	public ProfileChangeViewImpl() {
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void setProfileData(UserProfileRemote item) {
		System.out.println(">>>>> ProfileItem.setData()");
		if (item != null) {
			loginId.setValue(String.valueOf(item.getParentId()));
			profileId.setValue(String.valueOf(item.getId()));
			name.setText(item.getName());
			surname.setText(item.getSurname());
			email.setText(item.getEmail());
			gender_male.setValue(item.getGender() == 0 ? true : false);
			gender_female.setValue(item.getGender() == 0 ? false : true);

			// Image image = new Image(profileBundle.noprofile());
			// if (item.getProfileImage() != null && item.getProfileImage().getImage() != null) {
			// // add the real image to the panel
			// StringBuilder sb = new StringBuilder();
			// sb.append("data:image/gif;base64,");
			// sb.append(item.getProfileImage().getImage());
			//
			// image.setUrl(sb.toString());
			// }
			// image.setSize("175px", "200px");
			// imagePanel.clear();
			// imagePanel.add(image, "imageDiv");
		} else {
			// TODO
		}
	}

	@Override
	public UserProfileRemote updateProfileData(UserProfileRemote profile) {
		if (profileId.getValue() == profile.getId() && loginId.getValue() == profile.getParentId()) {
			// TODO: This check is important, and could throw an Exception if not
			System.out.println(">>>>> ProfileChangeView.updateProfileData --> IDs equal");
		}
		profile.setName(name.getText());
		profile.setSurname(surname.getText());
		profile.setEmail(email.getText());
		profile.setGender(gender_male.getValue() ? 0 : 1);
		return profile;
	}

	@Override
	public void cancelButtonClickHandler(ClickHandler clickHandler) {
		cancelBtn.addClickHandler(clickHandler);
	}

	@Override
	public void saveButtonClickHandler(ClickHandler clickHandler) {
		saveBtn.addClickHandler(clickHandler);
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