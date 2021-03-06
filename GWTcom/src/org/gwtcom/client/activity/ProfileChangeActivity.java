package org.gwtcom.client.activity;

import org.gwtcom.client.place.NewsListPlace;
import org.gwtcom.client.place.ProfileChangePlace;
import org.gwtcom.client.service.ProfileService;
import org.gwtcom.client.service.ProfileServiceAsync;
import org.gwtcom.client.view.profile.change.ProfileChangeView;
import org.gwtcom.shared.UserProfileRemote;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.smartgwt.client.util.SC;

public class ProfileChangeActivity extends AbstractActivity implements ProfileChangeView.Presenter {

	private final PlaceController _placeController;
	private final ProfileChangeView _profileChangeView;
	private UserProfileRemote _profile;

	@Inject
	public ProfileChangeActivity(ProfileChangeView profileChangeView, PlaceController placeController) {
		super();
		_placeController = placeController;
		_profileChangeView = profileChangeView;
		_profileChangeView.setPresenter(this);
		
		init();

	}

	private void init() {
		_profileChangeView.cancelButtonClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				_profileChangeView.setProfileData(_profile);
				SC.say("Change Profile", "Changes discarded");
			}
		});

		_profileChangeView.saveButtonClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				_profile = _profileChangeView.updateProfileData(_profile);
				ProfileServiceAsync service = GWT.create(ProfileService.class);
				service.updateUserProfile(_profile, new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						SC.say("Change Profile", "Changes saved");
					}

					@Override
					public void onFailure(Throwable caught) {
						SC.say("Change Profile", "Saving changes failed");
					}
				});
			}
		});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		System.out.println(">>>>>NewsItemPresenter.start()");
		final Place currentPlace = _placeController.getWhere();
		// are we right in here?
		if (currentPlace != null && currentPlace instanceof ProfileChangePlace) {
			String loginId = ((ProfileChangePlace) currentPlace).getId();

			getProfileView(loginId);
		} else {
			// back to news list
			goTo(new NewsListPlace());
		}
		panel.setWidget(_profileChangeView.asWidget());
	}

	private void getProfileView(String id) {
		System.out.println(">>>>> ProfileViewpresenter.getProfileView");
		ProfileServiceAsync service = GWT.create(ProfileService.class);
		service.getUserProfileByUserLoginId(id, new AsyncCallback<UserProfileRemote>() {

			@Override
			public void onSuccess(UserProfileRemote result) {
				_profile = result;
				_profileChangeView.setProfileData(_profile);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});

	}

	@Override
	public void goTo(Place place) {
		_placeController.goTo(place);
	}
}
