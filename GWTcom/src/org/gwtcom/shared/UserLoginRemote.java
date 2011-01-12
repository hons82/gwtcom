package org.gwtcom.shared;


public class UserLoginRemote extends BaseDomainRemote {

	private static final long serialVersionUID = 7680043766846376618L;

	private String _name;

	private String _username;

	public UserLoginRemote() {
	}

	public UserLoginRemote(long id, String name, String username) {
		setId(id);
		setName(name);
		setUsername(username);
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
