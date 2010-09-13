package org.gwtcom.server.domain;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(table = "newsItem")
public class NewsItem {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key _id;

	@Persistent(name = "dateAdded")
	private Date _dateAdded;

	@Persistent(name = "author")
	private String _author;

	@Persistent(name = "title")
	private String _title;

	@Persistent(name = "content")
	private String _content;

	public Key getId() {
		return _id;
	}

	public void setId(Key id) {
		_id = id;
	}

	public String getAuthor() {
		return _author;
	}

	public void setAuthor(String author) {
		_author = author;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
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

}
