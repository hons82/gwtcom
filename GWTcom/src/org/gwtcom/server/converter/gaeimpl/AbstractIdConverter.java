package org.gwtcom.server.converter.gaeimpl;

import org.gwtcom.server.converter.IIdConverter;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public abstract class AbstractIdConverter implements IIdConverter<Key, String> {

	@Override
	public String convertFromID(Key id) {
		return KeyFactory.keyToString(id);
	}

	@Override
	public Key convertToId(String encoded) {
		return KeyFactory.stringToKey(encoded);
	}

}
