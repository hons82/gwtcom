package org.gwtcom.client.presenter;

import java.util.List;

import org.gwtcom.client.event.DateItemShowEvent;
import org.gwtcom.client.event.EventBus;
import org.gwtcom.client.panel.navigation.NavBar;
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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DateListPresenter extends GeneralPresenter<DateListPresenter.Display> {

	public interface Display extends WidgetDisplay {

		HasClickHandlers getList();

		void setData(List<DateItemRemote> data);

		FlexTable getDateListTable();

		NavBar getNavBar();

		public void newer(List<DateItemRemote> data);

		public void older(List<DateItemRemote> data);

		int getClickedRow(ClickEvent event);
	}

	private List<DateItemRemote> _datelist;

	public static final Place PLACE = new Place("Date");

	@Inject
	public DateListPresenter(EventBus eventBus, Display display) {
		super(display, eventBus);
		bind();
	}

	@Override
	protected void onBind() {
		System.out.println(">>>>>DateListPresenter.onBind()");

		display.getDateListTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(">>>>>DateListPresenter.onClick()");

				int selectedRow = display.getClickedRow(event);

				if (selectedRow >= 0) {
					DateItemRemote item = _datelist.get(display.getClickedRow(event));
					eventBus.fireEvent(new DateItemShowEvent(item));
				}
			}

		});

		display.getNavBar().addNewerClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				display.newer(_datelist);
			}
		});

		display.getNavBar().addOlderClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				display.older(_datelist);
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		System.out.println(">>>>> DateListPresenter.go");
		// Because the gwt-presenter's MVP framework encourages the Presenters
		// to communicate by listening to PlaceRequestEvent, implementing this
		// method following the Google's MVP example will not work
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void refreshDisplay() {
		System.out.println(">>>>> DateListPresenter.refresh");
		display.setData(_datelist);
	}

	private void fetchDateList() {
		DatesServiceAsync service = GWT.create(DatesService.class);
		service.getPublicDates(new AsyncCallback<List<DateItemRemote>>() {
			public void onSuccess(List<DateItemRemote> result) {
				_datelist = result;
				refreshDisplay();
			}

			public void onFailure(Throwable caught) {
				Window.alert("Fail: " + caught.getMessage());
			}
		});
		// dispatcher.execute(new GetDateDetails(), new
		// AsyncCallback<GetDateDetailsResult>()
		// {
		// public void onSuccess(GetDateDetailsResult result)
		// {
		// _Datelist = result.getDateList();
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
		System.out.println(">>>>>>>>>> DateListPresenter.onPlaceRequest(PlaceRequest request)");
		fetchDateList();
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