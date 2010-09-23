package org.gwtcom.client.gin;

import org.gwtcom.client.AppController;
import org.gwtcom.client.place.PlaceManager;
import org.gwtcom.client.presenter.DateItemPresenter;
import org.gwtcom.client.presenter.DateListPresenter;
import org.gwtcom.client.presenter.NewsItemPresenter;
import org.gwtcom.client.presenter.NewsListPresenter;
import org.gwtcom.client.presenter.ProfileViewPresenter;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({ GWTcomClientModule.class })
public interface GWTcomGinjector extends Ginjector
{

	AppController getAppPresenter();

	PlaceManager getPlaceManager();

    NewsListPresenter getNewsListPresenter();

	NewsItemPresenter getNewsItemPresenter();
	
    DateListPresenter getDateListPresenter();

	DateItemPresenter getDateItemPresenter();

	ProfileViewPresenter getProfileViewPresenter();
}
