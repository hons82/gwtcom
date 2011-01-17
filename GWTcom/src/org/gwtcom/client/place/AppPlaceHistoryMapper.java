package org.gwtcom.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ NewsListPlace.Tokenizer.class, NewsItemPlace.Tokenizer.class, DateListPlace.Tokenizer.class,
		DateItemPlace.Tokenizer.class, ProfileViewPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
