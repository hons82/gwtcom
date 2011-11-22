package org.gwtcom.client.place;

import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PeopleViewPlace extends Place {

	private final String _id;

	public PeopleViewPlace(String id) {
		_id = id;
	}

	public PeopleViewPlace(UserLoginRemote item) {
		_id = String.valueOf(item.getId());
	}

	public String getId() {
		return _id;
	}

	public static class Tokenizer implements PlaceTokenizer<PeopleViewPlace> {
		@Override
		public String getToken(PeopleViewPlace place) {
			return place.getId();
		}

		@Override
		public PeopleViewPlace getPlace(String token) {
			return new PeopleViewPlace(token);
		}
	}

}
