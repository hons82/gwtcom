package org.gwtcom.client.activity;

import org.gwtcom.client.place.ProfileViewPlace;
import org.gwtcom.client.service.ProfileService;
import org.gwtcom.client.service.ProfileServiceAsync;
import org.gwtcom.client.view.profile.ProfileView;
import org.gwtcom.shared.UserProfileRemote;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class ProfileViewActivity extends AbstractActivity implements ProfileView.Presenter{

	private final PlaceController _placeController;
	private final ProfileView _profileView;
	private UserProfileRemote _profile;

	@Inject
	public ProfileViewActivity(ProfileView profileView, PlaceController placeController) {
		super();
		_placeController = placeController;
		_profileView = profileView;
		_profileView.setPresenter(this);
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		System.out.println(">>>>>NewsItemPresenter.start()");
		final Place currentPlace = _placeController.getWhere();
		if (currentPlace != null && currentPlace instanceof ProfileViewPlace) {
			getProfileView(Long.parseLong(((ProfileViewPlace) currentPlace).getId()));
		} else {
			// TODO: sent back to the List
		}
		panel.setWidget(_profileView.asWidget());
	}

	
	private void getProfileView(Long id) {
		System.out.println(">>>>> ProfileViewpresenter.getProfileView");
		ProfileServiceAsync service = GWT.create(ProfileService.class);
		service.getProfile(id, new AsyncCallback<UserProfileRemote>() {
			

			@Override
			public void onSuccess(UserProfileRemote result) {
				_profile = result;
				_profileView.setData(_profile);
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
