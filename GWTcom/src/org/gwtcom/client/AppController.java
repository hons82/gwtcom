package org.gwtcom.client;

import org.gwtcom.client.gin.GWTcomGinjector;
import org.gwtcom.client.place.DateItemPlace;
import org.gwtcom.client.place.DateListPlace;
import org.gwtcom.client.place.NewsItemPlace;
import org.gwtcom.client.place.NewsListPlace;
import org.gwtcom.client.place.ProfileViewPlace;
import org.gwtcom.client.view.GWTmainView;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;

public class AppController implements ActivityMapper {


	private final EventBus _eventbus;

	private final GWTcomGinjector _injector;

	private GWTmainView _gwtmain;

	private SimplePanel _container;

	@Inject
	public AppController(EventBus eventbus, GWTcomGinjector injector) {
		_eventbus = eventbus;
		_injector = injector;
	}

	public SimplePanel go(HasWidgets container) {
		_gwtmain = new GWTmainView(_eventbus);
		container.add(_gwtmain.asWidget());
		_container = _gwtmain.getDetailContainer();

		System.out.println("History: <" + History.getToken() + ">");
//		if ("".equals(History.getToken())) {
//			History.newItem(NewsListPresenter.PLACE.getId());
//		} else {
//			History.fireCurrentHistoryState();
//		}
		return _container;
	}

	@Override
	public Activity getActivity(Place place) {
		System.out.println(">>>>>AppController.onPlaceRequest");

		if (place instanceof NewsListPlace)
			return _injector.getNewsListActivity();
		else if (place instanceof NewsItemPlace)
			return _injector.getNewsItemActivity();
		else if (place instanceof DateListPlace)
			return _injector.getDateListActivity();
		else if (place instanceof DateItemPlace)
			return _injector.getDateItemActivity();
		else if (place instanceof ProfileViewPlace)
			return _injector.getProfileViewActivity();
		return null;
	}
	
}
