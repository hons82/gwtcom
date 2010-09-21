package org.gwtcom.client.presenter;

import org.gwtcom.client.event.bus.EventBus;
import org.gwtcom.client.place.Place;
import org.gwtcom.client.place.PlaceRequest;
import org.gwtcom.client.service.ProfileService;
import org.gwtcom.client.service.ProfileServiceAsync;
import org.gwtcom.shared.UserProfileRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ProfileViewPresenter extends GeneralPresenter<ProfileViewPresenter.Display> {

	public interface Display extends WidgetDisplay {

		HasClickHandlers getList();

		void setData(UserProfileRemote data);

	}

	private UserProfileRemote _profile;

	public static final Place PLACE = new Place("ProfileView");

	@Inject
	public ProfileViewPresenter(EventBus eventBus, Display display) {
		super(display, eventBus);
		bind();
	}

	@Override
	protected void onBind() {
		System.out.println(">>>>>ProfileViewPresenter.onBind()");

	}

	@Override
	public void go(HasWidgets container) {
		System.out.println(">>>>> ProfileViewPresenter.go");
		// Because the gwt-presenter's MVP framework encourages the Presenters
		// to communicate by listening to PlaceRequestEvent, implementing this
		// method following the Google's MVP example will not work
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void refreshDisplay() {
		System.out.println("ProfileViewPresenter.refresh");
		display.setData(_profile);
	}

	private void getProfileView(Long id) {
		System.out.println(">>>>> ProfileViewpresenter.getProfileView");
		ProfileServiceAsync service = GWT.create(ProfileService.class);
		service.getProfile(id, new AsyncCallback<UserProfileRemote>() {
			public void onSuccess(UserProfileRemote result) {
				_profile = result;
				refreshDisplay();
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	@Override
	public Place getPlace() {
		return PLACE;
	}

	@Override
	protected void onPlaceRequest(PlaceRequest request) {
		System.out.println(">>>>>>>>>> ProfileViewPresenter.onPlaceRequest(PlaceRequest request)");
		String profileId = request.getParameter("profileId", null);
		if (profileId != null) {
			try {
				getProfileView(Long.valueOf(profileId));
			} catch (Exception e) {
				refreshDisplay();
			}
		} else {
			refreshDisplay();
		}
	}

	@Override
	protected void onUnbind() {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>> ProfilePresenter.onUnbind()");
	}

	@Override
	public void revealDisplay() {
		// TODO Auto-generated method stub
	}

}