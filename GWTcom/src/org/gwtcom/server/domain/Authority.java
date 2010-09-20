package org.gwtcom.server.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Authority {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key _id;

	@Persistent(name = "authname")
	private String _authname;

	public Key getId() {
		return _id;
	}

	public void setId(Key id) {
		_id = id;
	}

	public void setAuthname(String authname) {
		_authname = authname;
	}

	public String getAuthname() {
		return _authname;
	}
}
