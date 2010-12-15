package org.gwtcom.client.place;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class AppPlaceController extends PlaceController {

	@Inject
	public AppPlaceController(EventBus eventBus) {
		super(eventBus);
	}

}
