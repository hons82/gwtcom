package org.gwtcom.client.place;

import org.gwtcom.shared.DateItemRemote;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DateItemPlace extends Place {
	
	private final String _id;
	
	public DateItemPlace(String id){
		_id = id;
	}

	public DateItemPlace(DateItemRemote item) {
		_id = String.valueOf(item.getId());
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
