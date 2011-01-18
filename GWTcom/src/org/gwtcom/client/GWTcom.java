package org.gwtcom.client;

import org.gwtcom.client.gin.GWTcomGinjector;
import org.gwtcom.client.place.AppPlaceHistoryMapper;
import org.gwtcom.client.place.NewsListPlace;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTcom implements EntryPoint {

	private final GWTcomGinjector injector = GWT.create(GWTcomGinjector.class);
	private final Place _defaultPlace = new NewsListPlace();

	interface GlobalResources extends ClientBundle {
		@NotStrict
		@Source("GWTcom.css")
		CssResource css();
	}

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {

		// Inject global styles.
		GWT.<GlobalResources> create(GlobalResources.class).css().ensureInjected();

		// Get rid of scrollbars, and clear out the window's built-in margin,
		// because we want to take advantage of the entire client area.
		Window.enableScrolling(false);
		Window.setMargin("0px");

		injector.getPlaceController();

		// If a Activity is providing a Place, I discovered that the Activity
		// has to be instantiated at the start of the application or else the Activitys
		// will will not handle PlaceRequestEvent properly and history mechanism
		// properly
		// This is the current behavior of gwt-Activity release 1.0. I hope
		// future version will provide lazy-loading of Activitys
		//
		/**/injector.getNewsListActivity(); /**/
		/**/injector.getNewsItemActivity(); /**/
		/**/injector.getDateListActivity(); /**/
		/**/injector.getDateItemActivity(); /**/
		/**/injector.getProfileViewActivity(); /**/
		/**/injector.getProfileChangeViewActivity(); /**/

		// AppController appActivity = injector.getAppActivity();
		// appActivity.go(RootLayoutPanel.get());

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper activityMapper = injector.getAppActivity();
		ActivityManager activityManager = new ActivityManager(activityMapper, injector.getEventBus());
		activityManager.setDisplay(((AppController) activityMapper).go(RootLayoutPanel.get()));

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(injector.getPlaceController(), injector.getEventBus(), _defaultPlace);

		// Goes to the place represented on URL else default place
		historyHandler.handleCurrentHistory();

	}

}