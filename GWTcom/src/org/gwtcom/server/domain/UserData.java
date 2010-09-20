package org.gwtcom.server.domain;

import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class UserData {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key _id;

	@Persistent(name = "name")
	private String _name;
	
	@Persistent(name = "surname")
	private String _surname;

	@Persistent(name = "email")
	private String _email;
	
	@Persistent(name = "gender")
	private int _gender;
	
	@Persistent(name = "profile", mappedBy = "_userdata")
	private UserProfile _profile;

	public UserData() {
	}

	public Key getId() {
		return _id;
	}

	public void setId(Key id) {
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

	public void setProfile(UserProfile profile) {
		_profile = profile;
	}

	public UserProfile getProfile() {
		return _profile;
	}

}
