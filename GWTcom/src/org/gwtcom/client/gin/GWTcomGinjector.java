package org.gwtcom.client.gin;

import org.gwtcom.client.AppActivityMapper;
import org.gwtcom.client.activity.DateItemActivity;
import org.gwtcom.client.activity.DateListActivity;
import org.gwtcom.client.activity.NewsChangeActivity;
import org.gwtcom.client.activity.NewsItemActivity;
import org.gwtcom.client.activity.NewsListActivity;
import org.gwtcom.client.activity.PeopleViewActivity;
import org.gwtcom.client.activity.ProfileChangeActivity;
import org.gwtcom.client.activity.ProfileViewActivity;
import org.gwtcom.client.i18n.GWTcomConstants;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

@GinModules({ GWTcomClientModule.class })
public interface GWTcomGinjector extends Ginjector {

	GWTcomConstants getGWTcomConstants();

	AppActivityMapper getAppActivityMapper();

	EventBus getEventBus();

	PlaceController getPlaceController();

	NewsListActivity getNewsListActivity();

	NewsItemActivity getNewsItemActivity();

	NewsChangeActivity getNewsChangeActivity();

	DateListActivity getDateListActivity();

	DateItemActivity getDateItemActivity();

	ProfileViewActivity getProfileViewActivity();

	ProfileChangeActivity getProfileChangeActivity();

	PeopleViewActivity getPeopleViewActivity();
}
