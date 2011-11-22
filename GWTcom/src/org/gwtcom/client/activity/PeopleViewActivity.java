package org.gwtcom.client.activity;

import org.gwtcom.client.place.NewsListPlace;
import org.gwtcom.client.place.PeopleViewPlace;
import org.gwtcom.client.view.people.PeopleView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class PeopleViewActivity extends AbstractActivity implements PeopleView.Presenter {

	private final PlaceController _placeController;
	private final PeopleView _peopleView;
//	private UserProfileRemote _profile;

	@Inject
	public PeopleViewActivity(PeopleView peopleView, PlaceController placeController) {
		super();
		_placeController = placeController;
		_peopleView = peopleView;
		_peopleView.setPresenter(this);
//		_peopleView.addWallPostClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				ProfileServiceAsync service = GWT.create(ProfileService.class);
//				service.addWallPost(_peopleView.getProfileId(), _peopleView.getWallPostInputContent(),
//						new AsyncCallback<WallEntryRemote>() {
//
//							@Override
//							public void onSuccess(WallEntryRemote result) {
//								_peopleView.addProfileWallEntry(result);
//							}
//
//							@Override
//							public void onFailure(Throwable caught) {
//								Window.alert(caught.getMessage());
//							}
//						});
//			}
//		});

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		System.out.println(">>>>>PeopleViewPresenter.start()");
		final Place currentPlace = _placeController.getWhere();
		if (currentPlace != null && currentPlace instanceof PeopleViewPlace) {
			String loginId = ((PeopleViewPlace) currentPlace).getId();

			//getPeopleView(loginId);
		} else {
			// back to news list
			goTo(new NewsListPlace());
		}
		panel.setWidget(_peopleView.asWidget());
	}

//	private void getPeopleView(String id) {
//		System.out.println(">>>>> ProfileViewpresenter.getProfileView");
//		ProfileServiceAsync service = GWT.create(ProfileService.class);
//		service.getUserProfileByUserLoginId(id, new AsyncCallback<UserProfileRemote>() {
//
//			@Override
//			public void onSuccess(UserProfileRemote result) {
//				_profile = result;
//				_peopleView.setProfileData(_profile);
//			}
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert(caught.getMessage());
//			}
//		});
//
//		service.getPublicWallEntries(id, new AsyncCallback<List<WallEntryRemote>>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				Window.alert(caught.getMessage());
//			}
//
//			@Override
//			public void onSuccess(List<WallEntryRemote> result) {
//				// TODO Auto-generated method stub
//				_peopleView.setProfileWallData(result);
//			}
//		});
//		
//		service.getPublicFriendEntries(id, new AsyncCallback<List<FriendEntryRemote>>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				Window.alert(caught.getMessage());
//			}
//
//			@Override
//			public void onSuccess(List<FriendEntryRemote> result) {
//				// TODO Auto-generated method stub
//				_peopleView.setProfileFriendsData(result);
//			}
//		});
//	}

	@Override
	public void goTo(Place place) {
		_placeController.goTo(place);
	}
}
