package org.gwtcom.server.domain;

import java.util.Set;
import java.util.TreeSet;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class UserProfile {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key _id;

	@Persistent(name = "name")
	private String _name;

	@Persistent(name = "pass")
	private String _password;

	@Persistent(name = "authorities")
	private Set<Key> _authorities;
	
	@Persistent(name = "userdata")
	private UserData _userdata;


	public UserProfile() {
		_authorities = new TreeSet<Key>();
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

	public void setPassword(String password) {
		_password = password;
	}

	public String getPassword() {
		return _password;
	}

	public void setAuthorities(Set<Key> authorities) {
		_authorities = authorities;
	}

	public Set<Key> getAuthorities() {
		return _authorities;
	}

	public void setUserdata(UserData userdata) {
		_userdata = userdata;
	}

	public UserData getUserdata() {
		return _userdata;
	}

}
