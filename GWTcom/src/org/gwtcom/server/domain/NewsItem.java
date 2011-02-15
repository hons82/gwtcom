package org.gwtcom.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@Entity
public class NewsItem extends BaseDomainObject {

	private static final long serialVersionUID = 8922196591001353438L;

	@Column(name = "author")
	private Key _author;

	@Column(name = "title")
	private String _title;

	@Column(name = "content")
	private Text _content;

	public NewsItem() {
		super();
	}

	public Key getAuthor() {
		return _author;
	}

	public void setAuthor(Key author) {
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
