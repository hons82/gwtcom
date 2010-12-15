package org.gwtcom.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DateListPlace extends Place {
	
	public static class Tokenizer implements PlaceTokenizer<DateListPlace> {
        @Override
        public String getToken(DateListPlace place) {
            return null;
        }

        @Override
        public DateListPlace getPlace(String token) {
            return new DateListPlace();
        }
    }

}
