package org.gwtcom.client.presenter;

import org.gwtcom.client.event.DateListShowEvent;
import org.gwtcom.client.event.EventBus;
import org.gwtcom.client.place.Place;
import org.gwtcom.client.place.PlaceRequest;
import org.gwtcom.client.service.DatesService;
import org.gwtcom.client.service.DatesServiceAsync;
import org.gwtcom.shared.DateItemRemote;

import com.google.gwt.core.client.GWT;
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
public class DateItemPresenter extends GeneralPresenter<DateItemPresenter.Display> {

	public interface Display extends WidgetDisplay {

		HasClickHandlers getList();

		void setData(DateItemRemote data);

		Button getBackButton();
	}

	private DateItemRemote _dateitem;

	public static final Place PLACE = new Place("DateItem");

	@Inject
	public DateItemPresenter(EventBus eventBus, Display display) {
		super(display, eventBus);
		bind();
	}

	@Override
	protected void onBind() {
		System.out.println(">>>>>DateItemPresenter.onBind()");

		display.getBackButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new DateListShowEvent());
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		System.out.println(">>>>> DateItemPresenter.go");
		// Because the gwt-presenter's MVP framework encourages the Presenters
		// to communicate by listening to PlaceRequestEvent, implementing this
		// method following the Google's MVP example will not work
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void refreshDisplay() {
		System.out.println("DateItemPresenter.refresh");
		display.setData(_dateitem);
	}

	private void getDateItem(Long id) {
		System.out.println(">>>>> DateItempresenter.getDateItem");
		DatesServiceAsync service = GWT.create(DatesService.class);
		service.getDateItem(id, new AsyncCallback<DateItemRemote>() {
			public void onSuccess(DateItemRemote result) {
				_dateitem = result;
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
		System.out.println(">>>>>>>>>> DateItemPresenter.onPlaceRequest(PlaceRequest request)");
		String DateId = request.getParameter("DateId", null);
		if (DateId != null) {
			try {
				getDateItem(Long.valueOf(DateId));
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
		System.out.println(">>>>>>>>>> DatePresenter.onUnbind()");
	}

	@Override
	public void revealDisplay() {
		// TODO Auto-generated method stub
	}

}