package org.gwtcom.client.activity;

import java.util.List;

import org.gwtcom.client.service.NewsService;
import org.gwtcom.client.service.NewsServiceAsync;
import org.gwtcom.client.view.news.NewsList;
import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;

public class NewsListActivity extends AbstractActivity implements NewsList.Presenter {

	private final ListDataProvider<NewsItemRemote> _newslist;
	private final PlaceController _placeController;
	private final NewsList _newsListView;

	@Inject
	public NewsListActivity(NewsList newsListView, PlaceController placeController) {
		super();
		System.out.println(">>>>>NewsListActivity()");
		_placeController = placeController;
		_newsListView = newsListView;
		_newsListView.setPresenter(this);
		_newslist = new ListDataProvider<NewsItemRemote>();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		System.out.println(">>>>>NewsListActivity.start()");

		fetchNewsList();
		panel.setWidget(_newsListView.asWidget());
		if (!_newslist.getDataDisplays().contains(_newsListView.getNewsList()))
			_newslist.addDataDisplay(_newsListView.getNewsList());
	}

	private void fetchNewsList() {
		NewsServiceAsync service = GWT.create(NewsService.class);
		service.getAllNews(new AsyncCallback<List<NewsItemRemote>>() {
			@Override
			public void onSuccess(List<NewsItemRemote> result) {
				System.out.println(">>>>>NewsListActivity.onSuccess()");
				_newslist.setList(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println(">>>>>NewsListActivity.onFailure()");
				Window.alert("Fail: " + caught.getMessage());
			}
		});
	}

	@Override
	public void goTo(Place place) {
		System.out.println(">>>>> NewsListActivity.goTo " + place.getClass().toString());
		_placeController.goTo(place);
	}

}