package org.gwtcom.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.google.appengine.api.datastore.Text;

@Entity
public class NewsItem extends BaseDomainObject {

	private static final long serialVersionUID = 8922196591001353438L;

	@Column(name = "dateAdded")
	private Date _dateAdded;

	@Column(name = "author")
	private UserProfile _author;

	@Column(name = "title")
	private String _title;

	@Column(name = "content")
	private Text _content;

	public NewsItem() {
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

	public Text getContent() {
		return _content;
	}

	public void setContent(Text content) {
		_content = content;
	}

	public void setDateAdded(Date dateAdded) {
		_dateAdded = dateAdded;
	}

	public Date getDateAdded() {
		return _dateAdded;
	}

}
