package org.gwtcom.server.converter.gaeimpl;

import org.gwtcom.server.domain.BaseDomainObject;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class AbstractIdConverter {

	public String convertToString(Key id) {
		return id != null ? String.valueOf(id.getId()) : "";
	}

	public Key convertToId(Class<? extends BaseDomainObject> type, String id) {
		return id != null ? KeyFactory.createKey(type.getSimpleName(), Long.parseLong(id)) : null;
	}
}
