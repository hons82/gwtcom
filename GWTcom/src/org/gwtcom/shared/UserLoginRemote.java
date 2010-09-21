package org.gwtcom.shared;

import java.io.Serializable;

public class UserLoginRemote implements Serializable {

	private Long _id;

	private String _name;

	private String _username;

	public UserLoginRemote() {
	}

	public UserLoginRemote(long id, String name, String username) {
		setId(id);
		setName(name);
		setUsername(username);
	}

	public Long getId() {
		return _id;
	}

	public void setId(Long id) {
		_id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setUsername(String username) {
		_username = username;
	}

	public String getUsername() {
		return _username;
	}

}
