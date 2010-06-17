package org.gwtcom.client.gin;

import org.gwtcom.client.AppController;
import org.gwtcom.client.place.PlaceManager;
import org.gwtcom.client.presenter.NewsListPresenter;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({ GWTcomClientModule.class })
public interface GWTcomGinjector extends Ginjector
{

	AppController getAppPresenter();

	PlaceManager getPlaceManager();

    NewsListPresenter getNewsListPresenter();
	//
	// EditContactPresenter getEditContactPresenter();

}
