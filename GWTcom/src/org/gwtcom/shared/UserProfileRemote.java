package org.gwtcom.shared;

import java.io.Serializable;

public class UserProfileRemote implements Serializable {

	private Long _id;

	private String _name;

	private String _surname;

	private String _email;

	private int _gender;

	public UserProfileRemote() {
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

	public void setSurname(String surname) {
		_surname = surname;
	}

	public String getSurname() {
		return _surname;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getEmail() {
		return _email;
	}

	public void setGender(int gender) {
		_gender = gender;
	}

	public int getGender() {
		return _gender;
	}

}
