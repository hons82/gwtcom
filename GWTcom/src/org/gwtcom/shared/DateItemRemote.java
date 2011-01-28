package org.gwtcom.shared;

import java.util.Date;

import com.google.gwt.view.client.ProvidesKey;

public class DateItemRemote extends BaseDomainRemote {

	/**
	 * The key provider that provides the unique ID
	 */
	public static final ProvidesKey<DateItemRemote> KEY_PROVIDER = new ProvidesKey<DateItemRemote>() {
		@Override
		public Object getKey(DateItemRemote item) {
			return item == null ? null : item.getId();
		}
	};

	private Date _dateAdded;
	private UserProfileRemote _author;
	private String _title;

	public DateItemRemote() {
		this(0l, new Date(System.currentTimeMillis()), null, "");
	}

	public DateItemRemote(Long id, Date dateAdded, UserProfileRemote author, String title) {
		super();
		setId(id);
		_dateAdded = dateAdded;
		_author = author;
		_title = title;
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
