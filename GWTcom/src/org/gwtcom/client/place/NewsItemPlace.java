package org.gwtcom.client.place;

import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NewsItemPlace extends Place {

	private final String _id;

	public NewsItemPlace(String id) {
		_id = id;
	}

	public NewsItemPlace(NewsItemRemote item) {
		_id = String.valueOf(item.getId());
	}

	public String getId() {
		return _id;
	}

	public static class Tokenizer implements PlaceTokenizer<NewsItemPlace> {
		@Override
		public String getToken(NewsItemPlace place) {
			return place.getId();
		}

		@Override
		public NewsItemPlace getPlace(String token) {
			return new NewsItemPlace(token);
		}
	}

}
