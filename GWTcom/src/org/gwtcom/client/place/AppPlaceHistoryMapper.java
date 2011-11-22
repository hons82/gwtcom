package org.gwtcom.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ NewsListPlace.Tokenizer.class, NewsItemPlace.Tokenizer.class, NewsChangePlace.Tokenizer.class,
		DateListPlace.Tokenizer.class, DateItemPlace.Tokenizer.class, ProfileViewPlace.Tokenizer.class,
		ProfileChangePlace.Tokenizer.class, PeopleViewPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
