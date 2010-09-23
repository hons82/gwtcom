package org.gwtcom.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DateItemRemote implements Serializable {
	private Long _id;
	private Date _dateAdded;
	private String _author;
	private String _title;

	public DateItemRemote() {
		this(0l, new Date(System.currentTimeMillis()), "", "");
	}

	public DateItemRemote(Long id, Date dateAdded, String author, String title) {
		super();
		_id = id;
		_dateAdded = dateAdded;
		_author = author;
		_title = title;
	}

	public Long getId() {
		return _id;
	}

	public void setId(Long id) {
		_id = id;
	}

	public Date getDateAdded() {
		return _dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		_dateAdded = dateAdded;
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

}
