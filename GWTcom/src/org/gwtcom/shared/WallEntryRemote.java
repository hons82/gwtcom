package org.gwtcom.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class WallEntryRemote implements Serializable {
	private Long _id;
	private Date _dateAdded;
	private UserProfileRemote _owner;
	private UserProfileRemote _author;
	private String _content;

	public WallEntryRemote() {
		this(0l, new Date(System.currentTimeMillis()), null, null,"");
	}

	public WallEntryRemote(Long id, Date dateAdded, UserProfileRemote owner, UserProfileRemote author, String content) {
		super();
		_id = id;
		_dateAdded = dateAdded;
		setAuthor(author);
		setOwner(owner);
		setContent(content);
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

	public void setOwner(UserProfileRemote owner) {
		_owner = owner;
	}

	public UserProfileRemote getOwner() {
		return _owner;
	}

	public void setAuthor(UserProfileRemote author) {
		_author = author;
	}

	public UserProfileRemote getAuthor() {
		return _author;
	}

	public void setContent(String content) {
		_content = content;
	}

	public String getContent() {
		return _content;
	}

}
