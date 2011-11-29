package org.gwtcom.server.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.google.appengine.api.datastore.Key;

@Entity
public class UserProfile extends BaseDomainObject {

	public static final int GENDER_MALE = 0;
	public static final int GENDER_FEMALE = 1;

	@Column(name = "firstname")
	private String _firstname;

	@Column(name = "lastname")
	private String _lastname;

	@Column(name = "email")
	private String _email;

	@Column(name = "gender")
	private int _gender;

	@Column(name = "image")
	@OneToOne(cascade = CascadeType.ALL)
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

	@Column(name = "friendlist")
	private List<Key> _friends;

	public UserProfile() {
		super();
		setWall(new ArrayList<Key>());
		setFriends(new ArrayList<Key>());
	}

	public UserProfile(String firstname, String lastname) {
		this();
		setFirstname(firstname);
		setLastname(lastname);
	}

	public String getFirstname() {
		return _firstname;
	}

	public void setFirstname(String firstname) {
		_firstname = firstname;
	}

	public void setLastname(String lastname) {
		_lastname = lastname;
	}

	public String getLastname() {
		return _lastname;
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
	
	public ProfileImage getProfileImage() {
		return _profileImage;
	}

	public void setProfileImage(ProfileImage profileImage) {
		_profileImage = profileImage;
	}

	public List<Key> getWall() {
		return _wall;
	}
	
	public void setWall(List<Key> wall) {
		_wall = wall;
	}
	
	public List<Key> getFriends() {
		return _friends;
	}

	public void setFriends(List<Key> friends) {
		_friends = friends;
	}

}
