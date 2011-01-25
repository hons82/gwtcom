package org.gwtcom.client.place;

import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ProfileChangePlace extends Place {

	private final String _id;

	public ProfileChangePlace(String id) {
		_id = id;
	}

	public ProfileChangePlace(UserLoginRemote item) {
		_id = String.valueOf(item.getId());
	}

	public String getId() {
		return _id;
	}

	public static class Tokenizer implements PlaceTokenizer<ProfileChangePlace> {
		@Override
		public String getToken(ProfileChangePlace place) {
			return place.getId();
		}

		@Override
		public ProfileChangePlace getPlace(String token) {
			return new ProfileChangePlace(token);
		}
	}

}
