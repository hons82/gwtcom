package org.gwtcom.client.activity;

import java.util.List;

import org.gwtcom.client.service.DatesService;
import org.gwtcom.client.service.DatesServiceAsync;
import org.gwtcom.client.view.dates.DateList;
import org.gwtcom.shared.DateItemRemote;

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

public class DateListActivity extends AbstractActivity implements DateList.Presenter {

	private final ListDataProvider<DateItemRemote> _datelist;
	private final PlaceController _placeController;
	private final DateList _dateListView;

	@Inject
	public DateListActivity(DateList DateListView, PlaceController placeController) {
		super();
		_placeController = placeController;
		_dateListView = DateListView;
		_dateListView.setPresenter(this);
		_datelist = new ListDataProvider<DateItemRemote>();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		System.out.println(">>>>>DateListPresenter.start()");

		fetchDateList();
		panel.setWidget(_dateListView.asWidget());
		if (!_datelist.getDataDisplays().contains(_dateListView.getDateList()))
			_datelist.addDataDisplay(_dateListView.getDateList());
	}

	private void fetchDateList() {
		DatesServiceAsync service = GWT.create(DatesService.class);
		service.getPublicDates(new AsyncCallback<List<DateItemRemote>>() {
			@Override
			public void onSuccess(List<DateItemRemote> result) {
				_datelist.setList(result);
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