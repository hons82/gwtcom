package org.gwtcom.client.activity;

import java.util.List;

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
import com.smartgwt.client.util.SC;

public class NewsChangeActivity extends AbstractActivity implements NewsChange.Presenter {

	private final ListDataProvider<NewsItemRemote> _newslist;
	private final PlaceController _placeController;
	private final NewsChange _newsChangeView;

	@Inject
	public NewsChangeActivity(NewsChange newsListChangeView, PlaceController placeController) {
		super();
		System.out.println(">>>>>NewsChangeActivity()");
		_placeController = placeController;
		_newsChangeView = newsListChangeView;
		_newsChangeView.setPresenter(this);
		_newslist = new ListDataProvider<NewsItemRemote>();

		init();
	}

	private void init() {
		_newsChangeView.addButtonClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				NewsServiceAsync service = GWT.create(NewsService.class);
				service.addNewsItem(new AsyncCallback<NewsItemRemote>() {

					@Override
					public void onSuccess(NewsItemRemote result) {
						fetchNewsList();
						_newsChangeView.getNewsList().getSelectionModel().setSelected(result, true);
						SC.say("Add News", "NewsItem Created");
					}

					@Override
					public void onFailure(Throwable caught) {
						SC.say("Add News", "Adding failed");
					}
				});

			}
		});

		_newsChangeView.removeButtonClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (_newsChangeView.getSelectedItem() != null) {
					NewsServiceAsync service = GWT.create(NewsService.class);
					service.deleteNewsItem(_newsChangeView.getSelectedItem(), new AsyncCallback<Boolean>() {

						@Override
						public void onSuccess(Boolean result) {
							SC.say("Delete News", "Item Deleted");
						}

						@Override
						public void onFailure(Throwable caught) {
							SC.say("Delete News", "Item removing failed");
						}
					});
				}
			}
		});

		_newsChangeView.cancelButtonClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (_newsChangeView.getSelectedItem() != null) {
					_newsChangeView.getNewsList().getSelectionModel()
							.setSelected(_newsChangeView.getSelectedItem(), false);
					SC.say("Change News", "Changes discarded");
				}
			}
		});
		_newsChangeView.saveButtonClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				NewsItemRemote selectedItem = _newsChangeView.getSelectedItem();
				if (selectedItem != null) {
					// update item with new values
					selectedItem.setTitle(_newsChangeView.getNewsTitle());
					selectedItem.setContent(_newsChangeView.getContentasHTML());
					NewsServiceAsync service = GWT.create(NewsService.class);
					service.updateNewsItem(selectedItem, new AsyncCallback<Boolean>() {

						@Override
						public void onSuccess(Boolean result) {
							SC.say("Change News", "Changes saved");
						}

						@Override
						public void onFailure(Throwable caught) {
							SC.say("Change News", "Saving changes failed");
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
		panel.setWidget(_newsChangeView.asWidget());
		if (!_newslist.getDataDisplays().contains(_newsChangeView.getNewsList()))
			_newslist.addDataDisplay(_newsChangeView.getNewsList());
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