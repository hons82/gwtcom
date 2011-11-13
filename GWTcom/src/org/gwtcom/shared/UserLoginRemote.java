package org.gwtcom.shared;

public class UserLoginRemote extends BaseDomainRemote {

	private String _name;

	private String _username;
	
	private String _profileId;

	public UserLoginRemote() {
	}

	public UserLoginRemote(String id, String name, String username) {
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

	public void setProfileId(String profileId) {
		_profileId = profileId;
	}

	public String getProfileId() {
		return _profileId;
	}

}
