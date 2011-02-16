package org.gwtcom.client.activity;

import java.util.List;

import org.gwt.mosaic.ui.client.InfoPanel;
import org.gwtcom.client.service.NewsService;
import org.gwtcom.client.service.NewsServiceAsync;
import org.gwtcom.client.view.news.change.NewsChange;
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
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;

public class NewsChangeActivity extends AbstractActivity implements NewsChange.Presenter {

	private final ListDataProvider<NewsItemRemote> _newslist;
	private final PlaceController _placeController;
	private final NewsChange _newsListChangeView;

	@Inject
	public NewsChangeActivity(NewsChange newsListChangeView, PlaceController placeController) {
		super();
		System.out.println(">>>>>NewsChangeActivity()");
		_placeController = placeController;
		_newsListChangeView = newsListChangeView;
		_newsListChangeView.setPresenter(this);
		_newslist = new ListDataProvider<NewsItemRemote>();

		init();
	}

	private void init() {
		_newsListChangeView.addButtonClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				NewsServiceAsync service = GWT.create(NewsService.class);
				service.addNewsItem(new AsyncCallback<NewsItemRemote>() {

					@Override
					public void onSuccess(NewsItemRemote result) {
						fetchNewsList();
						_newsListChangeView.getNewsList().getSelectionModel().setSelected(result, true);
						InfoPanel.show("Add News", "NewsItem Created");
					}

					@Override
					public void onFailure(Throwable caught) {
						InfoPanel.show("Add News", "Adding failed");
					}
				});

			}
		});

		_newsListChangeView.removeButtonClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (_newsListChangeView.getSelectedItem() != null) {
					NewsServiceAsync service = GWT.create(NewsService.class);
					service.deleteNewsItem(_newsListChangeView.getSelectedItem(), new AsyncCallback<Boolean>() {

						@Override
						public void onSuccess(Boolean result) {
							InfoPanel.show("Delete News", "Item Deleted");
						}

						@Override
						public void onFailure(Throwable caught) {
							InfoPanel.show("Delete News", "Item removing failed");
						}
					});
				}
			}
		});

		_newsListChangeView.cancelButtonClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (_newsListChangeView.getSelectedItem() != null) {
					_newsListChangeView.getNewsList().getSelectionModel()
							.setSelected(_newsListChangeView.getSelectedItem(), false);
					InfoPanel.show("Change News", "Changes discarded");
				}
			}
		});
		_newsListChangeView.saveButtonClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				NewsItemRemote selectedItem = _newsListChangeView.getSelectedItem();
				if (selectedItem != null) {
					// update item with new values
					selectedItem.setContent(_newsListChangeView.getContentasHTML());
					NewsServiceAsync service = GWT.create(NewsService.class);
					service.updateNewsItem(selectedItem, new AsyncCallback<Boolean>() {

						@Override
						public void onSuccess(Boolean result) {
							InfoPanel.show("Change News", "Changes saved");
						}

						@Override
						public void onFailure(Throwable caught) {
							InfoPanel.show("Change News", "Saving changes failed");
						}
					});
				}
			}
		});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		System.out.println(">>>>>NewsListPresenter.start()");

		fetchNewsList();
		panel.setWidget(_newsListChangeView.asWidget());
		if (!_newslist.getDataDisplays().contains(_newsListChangeView.getNewsList()))
			_newslist.addDataDisplay(_newsListChangeView.getNewsList());
	}

	private void fetchNewsList() {
		NewsServiceAsync service = GWT.create(NewsService.class);
		service.getAllNews(new AsyncCallback<List<NewsItemRemote>>() {
			@Override
			public void onSuccess(List<NewsItemRemote> result) {
				_newslist.setList(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fail: " + caught.getMessage());
			}
		});
	}

	@Override
	public void goTo(Place place) {
		System.out.println(">>>>> NewsChangeActivity.goTo " + place.getClass().toString());
		_placeController.goTo(place);
	}

}