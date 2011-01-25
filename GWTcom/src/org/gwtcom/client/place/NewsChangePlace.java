package org.gwtcom.client.place;

import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NewsChangePlace extends Place {

	private final String _id;

	public NewsChangePlace(String id) {
		_id = id;
	}

	public NewsChangePlace(UserLoginRemote item) {
		_id = String.valueOf(item.getId());
	}

	public String getId() {
		return _id;
	}

	public static class Tokenizer implements PlaceTokenizer<NewsChangePlace> {
		@Override
		public String getToken(NewsChangePlace place) {
			return place.getId();
		}

		@Override
		public NewsChangePlace getPlace(String token) {
			return new NewsChangePlace(token);
		}
	}

}
