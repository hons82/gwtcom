package org.gwtcom.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DateItem extends BaseDomainObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2075374069026921484L;

	@Column(name = "dateAdded")
	private Date _dateAdded;

	@Column(name = "author")
	private UserProfile _author;

	@Column(name = "title")
	private String _title;

	@Column(name = "content")
	private String _content;

	public DateItem(){
		super();
	}
	
	public UserProfile getAuthor() {
		return _author;
	}

	public void setAuthor(UserProfile author) {
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
