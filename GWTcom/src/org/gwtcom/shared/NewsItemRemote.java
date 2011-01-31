package org.gwtcom.shared;

import java.util.Date;

import com.google.gwt.view.client.ProvidesKey;

public class NewsItemRemote extends BaseDomainRemote {

	/**
	 * The key provider that provides the unique ID
	 */
	public static final ProvidesKey<NewsItemRemote> KEY_PROVIDER = new ProvidesKey<NewsItemRemote>() {
		@Override
		public Object getKey(NewsItemRemote item) {
			return item == null ? null : item.getId();
		}
	};

	private Date _dateAdded;
	private UserProfileRemote _author;
	private String _title;
	private String _content;

	public NewsItemRemote() {
		new NewsItemRemote("", new Date(System.currentTimeMillis()), null, "", "");
	}

	public NewsItemRemote(String id, Date dateAdded, UserProfileRemote author, String title, String content) {
		super();
		setId(id);
		_dateAdded = dateAdded;
		_author = author;
		_title = title;
		_content = content;
	}

	public Date getDateAdded() {
		return _dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		_dateAdded = dateAdded;
	}

	public UserProfileRemote getAuthor() {
		return _author;
	}

	public void setAuthor(UserProfileRemote author) {
		_author = author;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setContent(String content) {
		_content = content;
	}

	public String getContent() {
		return _content;
	}

}
