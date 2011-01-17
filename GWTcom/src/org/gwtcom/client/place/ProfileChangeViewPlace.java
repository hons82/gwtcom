package org.gwtcom.client.place;

import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ProfileChangeViewPlace extends Place {

	private final String _id;

	public ProfileChangeViewPlace(String id) {
		_id = id;
	}

	public ProfileChangeViewPlace(UserLoginRemote item) {
		_id = String.valueOf(item.getId());
	}

	public String getId() {
		return _id;
	}

	public static class Tokenizer implements PlaceTokenizer<ProfileChangeViewPlace> {
		@Override
		public String getToken(ProfileChangeViewPlace place) {
			return place.getId();
		}

		@Override
		public ProfileChangeViewPlace getPlace(String token) {
			return new ProfileChangeViewPlace(token);
		}
	}

}
