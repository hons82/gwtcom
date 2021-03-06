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
import org.gwtcom.client.place.AppPlaceController;
import org.gwtcom.client.view.dates.DateItem;
import org.gwtcom.client.view.dates.DateItemImpl;
import org.gwtcom.client.view.dates.DateList;
import org.gwtcom.client.view.dates.DateListImpl;
import org.gwtcom.client.view.news.NewsItem;
import org.gwtcom.client.view.news.NewsItemImpl;
import org.gwtcom.client.view.news.NewsList;
import org.gwtcom.client.view.news.NewsListImpl;
import org.gwtcom.client.view.news.change.NewsChange;
import org.gwtcom.client.view.news.change.NewsChangeImpl;
import org.gwtcom.client.view.people.PeopleView;
import org.gwtcom.client.view.people.PeopleViewImpl;
import org.gwtcom.client.view.profile.ProfileView;
import org.gwtcom.client.view.profile.ProfileViewImpl;
import org.gwtcom.client.view.profile.change.ProfileChangeView;
import org.gwtcom.client.view.profile.change.ProfileChangeViewImpl;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class GWTcomClientModule extends AbstractGinModule {

	@Override
	protected void configure() {

		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

		bind(PlaceController.class).to(AppPlaceController.class).in(Singleton.class);

		// ***** DO NOT FORGET TO INITIALIZE PRESENTERS IN GWTcom.java *****

		bind(NewsListActivity.class).in(Singleton.class);

		bind(NewsList.class).to(NewsListImpl.class).in(Singleton.class);

		bind(NewsItemActivity.class).in(Singleton.class);

		bind(NewsItem.class).to(NewsItemImpl.class).in(Singleton.class);
		
		bind(NewsChangeActivity.class).in(Singleton.class);

		bind(NewsChange.class).to(NewsChangeImpl.class).in(Singleton.class);

		bind(DateListActivity.class).in(Singleton.class);

		bind(DateList.class).to(DateListImpl.class).in(Singleton.class);

		bind(DateItemActivity.class).in(Singleton.class);

		bind(DateItem.class).to(DateItemImpl.class).in(Singleton.class);

		bind(ProfileViewActivity.class).in(Singleton.class);

		bind(ProfileView.class).to(ProfileViewImpl.class).in(Singleton.class);

		bind(ProfileChangeActivity.class).in(Singleton.class);

		bind(ProfileChangeView.class).to(ProfileChangeViewImpl.class).in(Singleton.class);
		
		bind(PeopleViewActivity.class).in(Singleton.class);

		bind(PeopleView.class).to(PeopleViewImpl.class).in(Singleton.class);

		// ***** DO NOT FORGET TO INITIALIZE PRESENTERS IN GWTcom.java *****

		bind(AppActivityMapper.class).asEagerSingleton();

	}

}
