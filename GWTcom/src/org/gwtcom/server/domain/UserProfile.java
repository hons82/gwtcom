package org.gwtcom.server.domain;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class UserProfile {

	public static final int GENDER_MALE = 0;
	public static final int GENDER_FEMALE = 1;

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

	@Persistent(name = "login", mappedBy = "_userprofile")
	private UserLogin _login;
	
//	@Persistent(name = "newslist", mappedBy = "_author")
//	private List<NewsItem> _newslist;
//	
//	@Persistent(name = "dateslist", mappedBy = "_author")
//	private List<DateItem> _dateslist;
	
	@Persistent(name = "walllist", mappedBy = "_owner")
	private List<WallEntry> _wall;

	public UserProfile() {
		this(null,null);
	}

	public UserProfile(String name, String surname) {
		setName(name);
		setSurname(surname);
//		setNewslist(new ArrayList<NewsItem>());
//		setDateslist(new ArrayList<DateItem>());
		setWall(new ArrayList<WallEntry>());
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

	public void setLogin(UserLogin login) {
		_login = login;
	}

	public UserLogin getLogin() {
		return _login;
	}

//	public void setNewslist(List<NewsItem> newslist) {
//		_newslist = newslist;
//	}
//
//	public List<NewsItem> getNewslist() {
//		return _newslist;
//	}
//
//	public void setDateslist(List<DateItem> dateslist) {
//		_dateslist = dateslist;
//	}
//
//	public List<DateItem> getDateslist() {
//		return _dateslist;
//	}

	public void setWall(List<WallEntry> wall) {
		_wall = wall;
	}

	public List<WallEntry> getWall() {
		return _wall;
	}

}
