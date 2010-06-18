package org.gwtcom.client.presenter;

import java.util.List;

import org.gwtcom.client.event.EventBus;
import org.gwtcom.client.place.Place;
import org.gwtcom.client.place.PlaceRequest;
import org.gwtcom.client.service.NewsService;
import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class NewsListPresenter extends GeneralPresenter<NewsListPresenter.Display> {

	public interface Display extends WidgetDisplay {

		HasClickHandlers getList();

		void setData(List<NewsItemRemote> data);

		void showNewsItem(NewsItemRemote item);

		FlexTable getNewsListTable();

		int getClickedRow(ClickEvent event);
	}

	private List<NewsItemRemote> _newslist;

	public static final Place PLACE = new Place("News");

	@Inject
	public NewsListPresenter(EventBus eventBus, Display display) {
		super(display, eventBus);
		bind();
	}

	@Override
	protected void onBind() {
		System.out.println(">>>>>NewsListPresenter.onBind()");

		display.getNewsListTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(">>>>>NewsListPresenter.onClick()");

				openSelectedNews(event);
			}

		});

		// display.getAddButton().addClickHandler(new ClickHandler()
		// {
		// public void onClick(ClickEvent event)
		// {
		// eventBus.fireEvent(new AddContactEvent());
		// }
		// });
		//
	}

	private void openSelectedNews(ClickEvent event) {
		int selected = display.getClickedRow(event);
		System.out.println(">>>>> openSelectedNews " + selected + "(" + _newslist.size() + ")");
		if (selected >= 0 && selected < _newslist.size()) {
			NewsItemRemote item = _newslist.get(display.getClickedRow(event));
			display.showNewsItem(item);
		}
	}

	@Override
	public void go(HasWidgets container) {
		System.out.println(">>>>> NewsListPresenter.go");
		// Because the gwt-presenter's MVP framework encourages the Presenters
		// to communicate by listening to PlaceRequestEvent, implementing this
		// method following the Google's MVP example will not work
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void refreshDisplay() {
		System.out.println("Presenter.refresh");
		display.setData(_newslist);
	}

	public void setContactDetails(List<NewsItemRemote> contactDetails) {
		_newslist = contactDetails;
	}

	public NewsItemRemote getContactDetail(int index) {
		return _newslist.get(index);
	}

	private void fetchContactDetails() {
		NewsService.Util.getInstance().getPublicNews(new AsyncCallback<List<NewsItemRemote>>() {
			public void onSuccess(List<NewsItemRemote> result) {
				_newslist = result;
				refreshDisplay();
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
		// dispatcher.execute(new GetNewsDetails(), new
		// AsyncCallback<GetNewsDetailsResult>()
		// {
		// public void onSuccess(GetNewsDetailsResult result)
		// {
		// _newslist = result.getNewsList();
		// refreshDisplay();
		// }
		//	
		// public void onFailure(Throwable caught)
		// {
		// caught.printStackTrace();
		//	
		// Window.alert("Error fetching contact details");
		// }
		// });

	}

	@Override
	public Place getPlace() {
		return PLACE;
	}

	@Override
	protected void onPlaceRequest(PlaceRequest request) {
		System.out.println(">>>>>>>>>> NewsPresenter.onPlaceRequest(PlaceRequest request)");
		fetchContactDetails();
	}

	@Override
	protected void onUnbind() {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>> NewsPresenter.onUnbind()");
	}

	@Override
	public void revealDisplay() {
		// TODO Auto-generated method stub
	}

}