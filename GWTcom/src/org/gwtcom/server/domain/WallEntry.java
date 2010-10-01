package org.gwtcom.server.domain;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class WallEntry {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key _id;

	@Persistent(name = "dateAdded")
	private Date _dateAdded;

	@Persistent(name = "owner")
	private UserProfile _owner;
	
	@Persistent(name = "author")
	private UserProfile _author;

	@Persistent(name = "content")
	private String _content;

	public Key getId() {
		return _id;
	}

	public void setId(Key id) {
		_id = id;
	}

	public UserProfile getAuthor() {
		return _author;
	}

	public void setAuthor(UserProfile author) {
		_author = author;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setDateAdded(Date dateAdded) {
		_dateAdded = dateAdded;
	}

	public Date getDateAdded() {
		return _dateAdded;
	}

	public void setOwner(UserProfile owner) {
		_owner = owner;
	}

	public UserProfile getOwner() {
		return _owner;
	}

}
