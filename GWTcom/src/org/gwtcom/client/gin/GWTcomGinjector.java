package org.gwtcom.client.gin;

import org.gwtcom.client.AppController;
import org.gwtcom.client.activity.DateItemActivity;
import org.gwtcom.client.activity.DateListActivity;
import org.gwtcom.client.activity.NewsItemActivity;
import org.gwtcom.client.activity.NewsListActivity;
import org.gwtcom.client.activity.ProfileViewActivity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;

@GinModules({ GWTcomClientModule.class })
public interface GWTcomGinjector extends Ginjector {

	AppController getAppActivity();

	EventBus getEventBus();

	PlaceController getPlaceController();

	NewsListActivity getNewsListActivity();

	NewsItemActivity getNewsItemActivity();

	DateListActivity getDateListActivity();

	DateItemActivity getDateItemActivity();

	ProfileViewActivity getProfileViewActivity();
}
