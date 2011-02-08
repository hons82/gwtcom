package org.gwtcom.server.domain;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.google.appengine.api.datastore.Key;

@Entity
public class UserLogin extends BaseDomainObject {

	private static final long serialVersionUID = 5988243300695711066L;

	@Column(name = "name")
	private String _name;

	@Column(name = "pass")
	private String _password;

	@Column(name = "authorities")
	private Set<Key> _authorities;

	@Column(name = "userprofile")
	@OneToOne(cascade=CascadeType.ALL)
	private UserProfile _userprofile;

	public UserLogin() {
		super();
		_authorities = new TreeSet<Key>();
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

	public void setUserprofile(UserProfile userprofile) {
		_userprofile = userprofile;
	}

	public UserProfile getUserprofile() {
		return _userprofile;
	}

}
