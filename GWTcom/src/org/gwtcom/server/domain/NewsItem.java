package org.gwtcom.server.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NewsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;
    
    private Date _dateAdded;

    private String _author;

    private String _title;
    
    private String _content;

	public Long getId() {
		return _id;
	}

	public void setId(Long id) {
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
