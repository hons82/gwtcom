package org.gwtcom.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.appengine.api.datastore.Key;

@Entity
@MappedSuperclass
public abstract class BaseDomainObject {

	private static final long serialVersionUID = 8043009673990162687L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key _id;

	@Column(name = "dateAdded")
	private Date _dateAdded;

	@Column(name = "dateLastUpdate")
	private Date _dateLastUpdate;

	@Column(name = "userLastUpdate")
	private Key _userLastUpdate;

	@Column(name = "dateDeleted")
	private Date _dateDeleted;

	@Column(name = "userLastChange")
	private Key _userDeleted;

	public Key getId() {
		return _id;
	}

	public void setId(Key id) {
		_id = id;
	}

	public Date getDateAdded() {
		return _dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		_dateAdded = dateAdded;
	}

	public Date getDateLastUpdate() {
		return _dateLastUpdate;
	}

	public void setDateLastUpdate(Date dateLastUpdate) {
		_dateLastUpdate = dateLastUpdate;
	}

	public Key getUserLastUpdate() {
		return _userLastUpdate;
	}

	public void setUserLastUpdate(Key userLastUpdate) {
		_userLastUpdate = userLastUpdate;
	}

	public Date getDateDeleted() {
		return _dateDeleted;
	}

	public void setDateDeleted(Date dateDeleted) {
		_dateDeleted = dateDeleted;
	}

	public Key getUserDeleted() {
		return _userDeleted;
	}

	public void setUserDeleted(Key userDeleted) {
		_userDeleted = userDeleted;
	}

}
