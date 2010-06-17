package org.gwtcom.client.gin;

import org.gwtcom.client.AppController;
import org.gwtcom.client.event.DefaultEventBus;
import org.gwtcom.client.event.EventBus;
import org.gwtcom.client.panel.news.NewsList;
import org.gwtcom.client.place.PlaceManager;
import org.gwtcom.client.presenter.NewsListPresenter;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class GWTcomClientModule extends  AbstractGinModule{

	@Override
	protected void configure() {
		
		bind(AppController.class).in(Singleton.class);
		
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		
		bind(PlaceManager.class).in(Singleton.class);

		bind(NewsListPresenter.class).in(Singleton.class);

		bind(NewsListPresenter.Display.class).to(NewsList.class).in(Singleton.class);
		
//		bind(EditContactPresenter.class).in(Singleton.class);
//		
//		bind(EditContactPresenter.Display.class).to(EditContactView.class).in(Singleton.class);
//		
	}

}
