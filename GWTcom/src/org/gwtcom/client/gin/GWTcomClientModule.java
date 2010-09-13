package org.gwtcom.client.gin;

import org.gwtcom.client.AppController;
import org.gwtcom.client.event.DefaultEventBus;
import org.gwtcom.client.event.EventBus;
import org.gwtcom.client.panel.dates.DateItem;
import org.gwtcom.client.panel.dates.DateList;
import org.gwtcom.client.panel.news.NewsItem;
import org.gwtcom.client.panel.news.NewsList;
import org.gwtcom.client.place.PlaceManager;
import org.gwtcom.client.presenter.DateItemPresenter;
import org.gwtcom.client.presenter.DateListPresenter;
import org.gwtcom.client.presenter.NewsItemPresenter;
import org.gwtcom.client.presenter.NewsListPresenter;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class GWTcomClientModule extends AbstractGinModule {

	@Override
	protected void configure() {

		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);

		bind(PlaceManager.class).in(Singleton.class);

		// ***** DO NOT FORGET TO INITIALIZE PRESENTERS IN GWTcom.java *****

		bind(NewsListPresenter.class).in(Singleton.class);

		bind(NewsListPresenter.Display.class).to(NewsList.class).in(Singleton.class);

		bind(NewsItemPresenter.class).in(Singleton.class);

		bind(NewsItemPresenter.Display.class).to(NewsItem.class).in(Singleton.class);
		
		bind(DateListPresenter.class).in(Singleton.class);

		bind(DateListPresenter.Display.class).to(DateList.class).in(Singleton.class);

		bind(DateItemPresenter.class).in(Singleton.class);

		bind(DateItemPresenter.Display.class).to(DateItem.class).in(Singleton.class);

		// ***** DO NOT FORGET TO INITIALIZE PRESENTERS IN GWTcom.java *****

		bind(AppController.class).in(Singleton.class);

	}

}
