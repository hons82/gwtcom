package org.gwtcom.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DateItemPlace extends Place {
	
	private final String _id;
	
	public DateItemPlace(String id){
		_id = id;
	}

	public String getId(){
		return _id;
	}
	
	public static class Tokenizer implements PlaceTokenizer<DateItemPlace> {
        @Override
        public String getToken(DateItemPlace place) {
            return place.getId();
        }

        @Override
        public DateItemPlace getPlace(String token) {
            return new DateItemPlace(token);
        }
    }

}
