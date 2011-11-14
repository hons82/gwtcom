package org.gwtcom.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.google.appengine.api.datastore.Text;

@Entity
public class DateItem extends BaseDomainObject {

	@Column(name = "author")
	private UserProfile _author;

	@Column(name = "title")
	private String _title;

	@Column(name = "content")
	private Text _content;

	public DateItem() {
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

}
