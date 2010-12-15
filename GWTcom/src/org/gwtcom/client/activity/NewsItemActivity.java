package org.gwtcom.client.activity;

import org.gwtcom.client.event.NewsListShowEvent;
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

public class NewsItemActivity extends AbstractActivity implements NewsItem.Presenter{

	private final PlaceController _placeController;
	private NewsItemRemote _newsitem;
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
		System.out.println(">>>>>NewsItemPresenter.start()");

		_eventBus = eventBus;
		_newsItemView.getBackButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				_eventBus.fireEvent(new NewsListShowEvent());
			}
		});
		getNewsItem(1L);
		panel.setWidget(_newsItemView.asWidget());
	}

	private void getNewsItem(Long id) {
		System.out.println(">>>>> NewsItempresenter.getNewsItem");
		NewsServiceAsync service = GWT.create(NewsService.class);
		service.getNewsItem(id, new AsyncCallback<NewsItemRemote>() {
			@Override
			public void onSuccess(NewsItemRemote result) {
				_newsitem = result;
				_newsItemView.setData(_newsitem);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	@Override
	public void goTo(Place place) {
		_placeController.goTo(place);
	}

}