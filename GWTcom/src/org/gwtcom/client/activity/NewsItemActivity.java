package org.gwtcom.client.activity;

import org.gwtcom.client.place.NewsItemPlace;
import org.gwtcom.client.place.NewsListPlace;
import org.gwtcom.client.service.NewsService;
import org.gwtcom.client.service.NewsServiceAsync;
import org.gwtcom.client.view.news.NewsItem;
import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class NewsItemActivity extends AbstractActivity implements NewsItem.Presenter {

	private final PlaceController _placeController;
	private NewsItemRemote _newsItem;
	private EventBus _eventBus;
	private final NewsItem _newsItemView;

	@Inject
	public NewsItemActivity(NewsItem NewsItemView, PlaceController placeController) {
		super();
		_placeController = placeController;
		_newsItemView = NewsItemView;
		_newsItemView.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		System.out.println(">>>>>NewsItemActivity.start()");

		_eventBus = eventBus;
		_newsItemView.getBackButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				goTo(new NewsListPlace());
			}
		});
		final Place currentPlace = _placeController.getWhere();
		if (currentPlace != null && currentPlace instanceof NewsItemPlace) {
			getNewsItem(Long.parseLong(((NewsItemPlace) currentPlace).getId()));
		} else {
			// TODO: sent back to the List
		}
		panel.setWidget(_newsItemView.asWidget());
	}

	private void getNewsItem(Long id) {
		System.out.println(">>>>> NewsItemActivity.getNewsItem");
		NewsServiceAsync service = GWT.create(NewsService.class);
		service.getNewsItem(id, new AsyncCallback<NewsItemRemote>() {
			@Override
			public void onSuccess(NewsItemRemote result) {
				_newsItem = result;
				_newsItemView.setData(_newsItem);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	public void setNewsItemId(Long id) {
		if (_newsItem == null) {
			_newsItem = new NewsItemRemote();
		}
		_newsItem.setId(id);
	}

	@Override
	public void goTo(Place place) {
		System.out.println(">>>>> NewsItemActivity.goto");
		_placeController.goTo(place);
	}

}