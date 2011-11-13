package org.gwtcom.shared;

import java.util.Date;

public class WallEntryRemote extends BaseDomainRemote {
	
	private Date _dateAdded;
	
	private UserProfileRemote _owner;
	
	private UserProfileRemote _author;
	
	private String _content;

	public WallEntryRemote() {
		this("", new Date(System.currentTimeMillis()), null, null, "");
	}

	public WallEntryRemote(String id, Date dateAdded, UserProfileRemote owner, UserProfileRemote author, String content) {
		super();
		setId(id);
		setDateAdded(dateAdded);
		setAuthor(author);
		setOwner(owner);
		setContent(content);
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
