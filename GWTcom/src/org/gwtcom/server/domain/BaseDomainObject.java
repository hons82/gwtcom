package org.gwtcom.server.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.appengine.api.datastore.Key;

@Entity
@MappedSuperclass
public abstract class BaseDomainObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8043009673990162687L;

	@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key _id;

	public Key getId() {
		return _id;
	}

	public void setId(Key id) {
		_id = id;
	}
}
