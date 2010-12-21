package org.gwtcom.shared;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.view.client.ProvidesKey;

@SuppressWarnings("serial")
public class NewsItemRemote implements Serializable {

	/**
	 * The key provider that provides the unique ID of a contact.
	 */
	public static final ProvidesKey<NewsItemRemote> KEY_PROVIDER = new ProvidesKey<NewsItemRemote>() {
		@Override
		public Object getKey(NewsItemRemote item) {
			return item == null ? null : item.getId();
		}
	};

	private Long _id;
	private Date _dateAdded;
	private UserProfileRemote _author;
	private String _title;

	public NewsItemRemote() {
		new NewsItemRemote(0l, new Date(System.currentTimeMillis()), null, "");
	}

	public NewsItemRemote(Long id, Date dateAdded, UserProfileRemote author, String title) {
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

}
