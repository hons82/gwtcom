package org.gwtcom.client.activity;

import java.util.List;

import org.gwtcom.client.event.NewsItemShowEvent;
import org.gwtcom.client.service.NewsService;
import org.gwtcom.client.service.NewsServiceAsync;
import org.gwtcom.client.view.news.NewsList;
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

public class NewsListActivity extends AbstractActivity implements NewsList.Presenter{

	private List<NewsItemRemote> _newslist;
	private final PlaceController _placeController;
	private final NewsList _newsListView;
	private EventBus _eventBus;

	@Inject
	public NewsListActivity(NewsList newsListView, PlaceController placeController) {
		super();
		_placeController = placeController;
		_newsListView = newsListView;
		_newsListView.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		System.out.println(">>>>>NewsListPresenter.start()");

		_eventBus = eventBus;
		_newsListView.getNewsListTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(">>>>>NewsListPresenter.onClick()");

				int selectedRow = _newsListView.getClickedRow(event);

				if (selectedRow >= 0) {
					NewsItemRemote item = _newslist.get(_newsListView.getClickedRow(event));
					_eventBus.fireEvent(new NewsItemShowEvent(item));
				}
			}

		});

		_newsListView.getNavBar().addNewerClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				_newsListView.newer(_newslist);
			}
		});

		_newsListView.getNavBar().addOlderClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				_newsListView.older(_newslist);
			}
		});
		fetchNewsList();
		panel.setWidget(_newsListView.asWidget());
	}

	private void fetchNewsList() {
		NewsServiceAsync service = GWT.create(NewsService.class);
		service.getPublicNews(new AsyncCallback<List<NewsItemRemote>>() {
			@Override
			public void onSuccess(List<NewsItemRemote> result) {
				_newslist = result;
				_newsListView.setData(_newslist);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fail: " + caught.getMessage());
			}
		});
	}

	@Override
	public void goTo(Place place) {
		System.out.println(">>>>> NewsListActivity.goTo "+ place.getClass().toString());
		_placeController.goTo(place);
	}

}