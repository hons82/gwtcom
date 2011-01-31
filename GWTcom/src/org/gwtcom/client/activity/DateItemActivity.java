package org.gwtcom.client.activity;

import org.gwtcom.client.place.DateItemPlace;
import org.gwtcom.client.place.DateListPlace;
import org.gwtcom.client.service.DatesService;
import org.gwtcom.client.service.DatesServiceAsync;
import org.gwtcom.client.view.dates.DateItem;
import org.gwtcom.shared.DateItemRemote;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class DateItemActivity extends AbstractActivity implements DateItem.Presenter {

	private final PlaceController _placeController;
	private DateItemRemote _dateitem;
	private final DateItem _dateItemView;

	@Inject
	public DateItemActivity(DateItem DateItemView, PlaceController placeController) {
		super();
		_placeController = placeController;
		_dateItemView = DateItemView;
		_dateItemView.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		System.out.println(">>>>>DateItemPresenter.start()");

		final Place currentPlace = _placeController.getWhere();
		if (currentPlace != null && currentPlace instanceof DateItemPlace) {
			getDateItem(((DateItemPlace) currentPlace).getId());
		} else {
			goTo(new DateListPlace());
		}
		panel.setWidget(_dateItemView.asWidget());
	}

	private void getDateItem(String id) {
		System.out.println(">>>>> DateItempresenter.getDateItem");
		DatesServiceAsync service = GWT.create(DatesService.class);
		service.getDateItem(id, new AsyncCallback<DateItemRemote>() {
			@Override
			public void onSuccess(DateItemRemote result) {
				_dateitem = result;
				_dateItemView.setData(_dateitem);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	@Override
	public void goTo(Place place) {
		_placeController.goTo(place);
	}

}