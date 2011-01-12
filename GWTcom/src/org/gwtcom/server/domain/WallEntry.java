package org.gwtcom.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.google.appengine.api.datastore.Key;

@Entity
public class WallEntry extends BaseDomainObject {

	private static final long serialVersionUID = 4673308759394854464L;

	@Column(name = "dateAdded")
	private Date _dateAdded;

	@Column(name = "owner")
	private Key _owner;

	@Column(name = "author")
	private Key _author;

	@Column(name = "content")
	private String _content;

	public WallEntry() {
		super();
	}

	public Key getAuthor() {
		return _author;
	}

	public void setAuthor(Key author) {
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

	public void setOwner(Key owner) {
		_owner = owner;
	}

	public Key getOwner() {
		return _owner;
	}
}
