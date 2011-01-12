package org.gwtcom.server.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.google.appengine.api.datastore.Key;

@Entity
public class UserProfile extends BaseDomainObject {

	private static final long serialVersionUID = 382224708168422399L;

	public static final int GENDER_MALE = 0;
	public static final int GENDER_FEMALE = 1;

	@Column(name = "name")
	private String _name;

	@Column(name = "surname")
	private String _surname;

	@Column(name = "email")
	private String _email;

	@Column(name = "gender")
	private int _gender;

	@Column(name = "image")
	@OneToOne
	private ProfileImage _profileImage;

	@Column(name = "login")
	@OneToOne(mappedBy = "_userprofile")
	private UserLogin _login;

	// @Column(name = "newslist")
	// @OneToMany(mappedBy = "_author")
	// private List<NewsItem> _newslist;
	//
	// @Column(name = "dateslist")
	// @OneToMany(mappedBy = "_author")
	// private List<DateItem> _dateslist;
	//
	@Column(name = "walllist")
	private List<Key> _wall;

	public UserProfile() {
		this(null, null);
	}

	public UserProfile(String name, String surname) {
		super();
		setName(name);
		setSurname(surname);
		// setNewslist(new ArrayList<NewsItem>());
		// setDateslist(new ArrayList<DateItem>());
		setWall(new ArrayList<Key>());
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

	// public void setNewslist(List<NewsItem> newslist) {
	// _newslist = newslist;
	// }
	//
	// public List<NewsItem> getNewslist() {
	// return _newslist;
	// }
	//
	// public void setDateslist(List<DateItem> dateslist) {
	// _dateslist = dateslist;
	// }
	//
	// public List<DateItem> getDateslist() {
	// return _dateslist;
	// }
	//
	public void setWall(List<Key> wall) {
		_wall = wall;
	}

	public List<Key> getWall() {
		return _wall;
	}

	public void setProfileImage(ProfileImage profileImage) {
		_profileImage = profileImage;
	}

	public ProfileImage getProfileImage() {
		return _profileImage;
	}

}
