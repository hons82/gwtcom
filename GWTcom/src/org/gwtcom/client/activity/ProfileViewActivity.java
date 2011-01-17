package org.gwtcom.client.activity;

import java.util.List;

import org.gwtcom.client.place.NewsListPlace;
import org.gwtcom.client.place.ProfileViewPlace;
import org.gwtcom.client.service.ProfileService;
import org.gwtcom.client.service.ProfileServiceAsync;
import org.gwtcom.client.view.profile.ProfileView;
import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;

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

public class ProfileViewActivity extends AbstractActivity implements ProfileView.Presenter {

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
			final long loginId = Long.parseLong(((ProfileViewPlace) currentPlace).getId());
			_profileView.addWallPostClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					ProfileServiceAsync service = GWT.create(ProfileService.class);
					service.addWallPost(loginId, _profileView.getWallPostInputContent(),
							new AsyncCallback<WallEntryRemote>() {

								@Override
								public void onSuccess(WallEntryRemote result) {
									_profileView.addProfileWallEntry(result);
								}

								@Override
								public void onFailure(Throwable caught) {
									Window.alert(caught.getMessage());
								}
							});
				}
			});

			getProfileView(loginId);
		} else {
			// back to news list
			goTo(new NewsListPlace());
		}
		panel.setWidget(_profileView.asWidget());
	}

	private void getProfileView(Long id) {
		System.out.println(">>>>> ProfileViewpresenter.getProfileView");
		ProfileServiceAsync service = GWT.create(ProfileService.class);
		service.getUserProfileByUserLoginId(id, new AsyncCallback<UserProfileRemote>() {

			@Override
			public void onSuccess(UserProfileRemote result) {
				_profile = result;
				_profileView.setProfileData(_profile);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});

		service.getPublicWallEntries(id, new AsyncCallback<List<WallEntryRemote>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<WallEntryRemote> result) {
				// TODO Auto-generated method stub
				_profileView.setProfileWallData(result);
			}
		});
	}

	@Override
	public void goTo(Place place) {
		_placeController.goTo(place);
	}
}
