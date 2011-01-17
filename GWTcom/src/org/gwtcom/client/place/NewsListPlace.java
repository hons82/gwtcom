package org.gwtcom.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NewsListPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<NewsListPlace> {
		@Override
		public String getToken(NewsListPlace place) {
			return null;
		}

		@Override
		public NewsListPlace getPlace(String token) {
			return new NewsListPlace();
		}
	}

}
