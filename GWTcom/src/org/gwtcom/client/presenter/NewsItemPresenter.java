package org.gwtcom.client.presenter;

import org.gwtcom.client.event.EventBus;
import org.gwtcom.client.event.NewsListShowEvent;
import org.gwtcom.client.place.Place;
import org.gwtcom.client.place.PlaceRequest;
import org.gwtcom.client.service.NewsService;
import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class NewsItemPresenter extends GeneralPresenter<NewsItemPresenter.Display> {

	public interface Display extends WidgetDisplay {

		HasClickHandlers getList();

		void setData(NewsItemRemote data);

		Button getBackButton();
	}

	private NewsItemRemote _newsitem;

	public static final Place PLACE = new Place("NewsItem");

	@Inject
	public NewsItemPresenter(EventBus eventBus, Display display) {
		super(display, eventBus);
		bind();
	}

	@Override
	protected void onBind() {
		System.out.println(">>>>>NewsItemPresenter.onBind()");

		display.getBackButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new NewsListShowEvent());
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		System.out.println(">>>>> NewsItemPresenter.go");
		// Because the gwt-presenter's MVP framework encourages the Presenters
		// to communicate by listening to PlaceRequestEvent, implementing this
		// method following the Google's MVP example will not work
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void refreshDisplay() {
		System.out.println("NewsItemPresenter.refresh");
		display.setData(_newsitem);
	}

	private void getNewsItem(Long id) {
		System.out.println(">>>>> NewsItempresenter.getNewsItem");
		NewsService.Util.getInstance().getNewsItem(id, new AsyncCallback<NewsItemRemote>() {
			public void onSuccess(NewsItemRemote result) {
				_newsitem = result;
				refreshDisplay();
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	@Override
	public Place getPlace() {
		return PLACE;
	}

	@Override
	protected void onPlaceRequest(PlaceRequest request) {
		System.out.println(">>>>>>>>>> NewsItemPresenter.onPlaceRequest(PlaceRequest request)");
		String newsId = request.getParameter("newsId", null);
		if (newsId != null) {
			try {
				getNewsItem(Long.valueOf(newsId));
			} catch (Exception e) {
				refreshDisplay();
			}
		} else {
			refreshDisplay();
		}
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