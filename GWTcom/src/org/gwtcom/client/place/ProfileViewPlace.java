package org.gwtcom.client.place;

import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ProfileViewPlace extends Place {
	
	private final String _id;

	public ProfileViewPlace(String id) {
		_id = id;
	}

	public ProfileViewPlace(UserLoginRemote item) {
		_id = String.valueOf(item.getId());
	}

	public String getId() {
		return _id;
	}

	public static class Tokenizer implements PlaceTokenizer<ProfileViewPlace> {
		@Override
		public String getToken(ProfileViewPlace place) {
			return place.getId();
		}

		@Override
		public ProfileViewPlace getPlace(String token) {
			return new ProfileViewPlace(token);
		}
	}

}
