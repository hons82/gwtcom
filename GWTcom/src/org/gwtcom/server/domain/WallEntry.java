package org.gwtcom.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class WallEntry extends BaseDomainObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4673308759394854464L;

	@Column(name = "dateAdded")
	private Date _dateAdded;

	@Column(name = "owner")
	private UserProfile _owner;
	
	@Column(name = "author")
	private UserProfile _author;

	@Column(name = "content")
	private String _content;

	public WallEntry(){
		super();
	}
	
	public UserProfile getAuthor() {
		return _author;
	}

	public void setAuthor(UserProfile author) {
		_author = author;
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

	public void setOwner(UserProfile owner) {
		_owner = owner;
	}

	public UserProfile getOwner() {
		return _owner;
	}

}
