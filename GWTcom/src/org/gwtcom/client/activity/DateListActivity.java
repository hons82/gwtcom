package org.gwtcom.client.activity;

import java.util.List;

import org.gwtcom.client.event.DateItemShowEvent;
import org.gwtcom.client.service.DatesService;
import org.gwtcom.client.service.DatesServiceAsync;
import org.gwtcom.client.view.dates.DateList;
import org.gwtcom.shared.DateItemRemote;

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

public class DateListActivity extends AbstractActivity implements DateList.Presenter{

	private List<DateItemRemote> _datelist;
	private final PlaceController _placeController;
	private final DateList _dateListView;
	private EventBus _eventBus;

	@Inject
	public DateListActivity(DateList DateListView, PlaceController placeController) {
		super();
		_placeController = placeController;
		_dateListView = DateListView;
		_dateListView.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		System.out.println(">>>>>DateListPresenter.start()");

		_eventBus = eventBus;
		_dateListView.getDateListTable().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(">>>>>DateListPresenter.onClick()");

				int selectedRow = _dateListView.getClickedRow(event);

				if (selectedRow >= 0) {
					DateItemRemote item = _datelist.get(_dateListView.getClickedRow(event));
					_eventBus.fireEvent(new DateItemShowEvent(item));
				}
			}

		});

		_dateListView.getNavBar().addNewerClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				_dateListView.newer(_datelist);
			}
		});

		_dateListView.getNavBar().addOlderClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				_dateListView.older(_datelist);
			}
		});
		fetchDateList();
		panel.setWidget(_dateListView.asWidget());
	}

	private void fetchDateList() {
		DatesServiceAsync service = GWT.create(DatesService.class);
		service.getPublicDates(new AsyncCallback<List<DateItemRemote>>() {
			@Override
			public void onSuccess(List<DateItemRemote> result) {
				_datelist = result;
				_dateListView.setData(_datelist);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fail: " + caught.getMessage());
			}
		});
	}

	@Override
	public void goTo(Place place) {
		_placeController.goTo(place);
	}

}