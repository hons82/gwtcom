package org.gwtcom.client;

import org.gwtcom.client.event.EventBus;
import org.gwtcom.client.event.INewsItemShowEvent;
import org.gwtcom.client.event.INewsListShowEvent;
import org.gwtcom.client.event.NewsItemShowEvent;
import org.gwtcom.client.event.NewsListShowEvent;
import org.gwtcom.client.gin.GWTcomGinjector;
import org.gwtcom.client.panel.GWTmainView;
import org.gwtcom.client.place.PlaceRequestEvent;
import org.gwtcom.client.place.PlaceRequestHandler;
import org.gwtcom.client.presenter.Display;
import org.gwtcom.client.presenter.GeneralPresenter;
import org.gwtcom.client.presenter.NewsItemPresenter;
import org.gwtcom.client.presenter.NewsListPresenter;
import org.gwtcom.client.presenter.Presenter;
import org.gwtcom.client.presenter.WidgetDisplay;
import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class AppController implements Presenter, PlaceRequestHandler {

	private HasWidgets _container;

	private EventBus _eventbus;

	private GWTcomGinjector _injector;

	private GeneralPresenter<? extends WidgetDisplay> _presenter;

	private GWTmainView _gwtmain;

	@Inject
	public AppController(EventBus eventbus, GWTcomGinjector injector) {
		_eventbus = eventbus;
		_injector = injector;

		bind();
	}

	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		_gwtmain = new GWTmainView();
		container.add((Widget) _gwtmain.asWidget());
		_container = _gwtmain.getDetailContainer();

		System.out.println("History: <" + History.getToken() + ">");
		if ("".equals(History.getToken())) {
			History.newItem(NewsListPresenter.PLACE.getId());
		} else {
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void onPlaceRequest(PlaceRequestEvent event) {
		System.out.println(">>>>>AppController.onPlaceRequest");
		String id = event.getRequest().getPlace().getId();

		if (id.equals(NewsListPresenter.PLACE.getId()))
			_presenter = _injector.getNewsListPresenter();
		else if (id.equals(NewsItemPresenter.PLACE.getId()))
			_presenter = _injector.getNewsItemPresenter();

		refreshDisplay();
	}

	@Override
	public void bind() {
		_eventbus.addHandler(PlaceRequestEvent.getType(), this);

		_eventbus.addHandler(NewsListShowEvent.TYPE, new INewsListShowEvent() {

			@Override
			public void onNewsListShow(NewsListShowEvent event) {
				doShowNewsList();
			}
		});
		
		_eventbus.addHandler(NewsItemShowEvent.TYPE, new INewsItemShowEvent() {

			@Override
			public void onNewsItemShow(NewsItemShowEvent event) {
				doShowNewsItem(event.getItem());
			}
		});
	}

	private void doShowNewsItem(NewsItemRemote item) {
		History.newItem(NewsItemPresenter.PLACE.requestWith("newsId", item.getId().toString()).toString());
	}
	
	private void doShowNewsList() {
		History.newItem(NewsListPresenter.PLACE.toString());
	}

	@Override
	public Display getDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshDisplay() {
		_container.clear();
		if (_presenter != null) {
			_presenter.go(_container);
		}
	}

	@Override
	public void revealDisplay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unbind() {
		// TODO Auto-generated method stub

	}

}
