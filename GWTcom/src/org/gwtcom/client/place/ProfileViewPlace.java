package org.gwtcom.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ProfileViewPlace extends Place {
	
	public static class Tokenizer implements PlaceTokenizer<ProfileViewPlace> {
        @Override
        public String getToken(ProfileViewPlace place) {
            return null;
        }

        @Override
        public ProfileViewPlace getPlace(String token) {
            return new ProfileViewPlace();
        }
    }

}
