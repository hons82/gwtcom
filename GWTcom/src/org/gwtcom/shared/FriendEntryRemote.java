package org.gwtcom.shared;

public class FriendEntryRemote extends ParentBaseDomainRemote {
	
	private String _firstname;

	private String _lastname;

	private String _email;

	private int _gender;

	private ProfileImageRemote _profileImage;

	
	public FriendEntryRemote() {

	}

	public String getFirstname() {
		return _firstname;
	}

	public void setName(String firstname) {
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

	public void setProfileImage(ProfileImageRemote profileImage) {
		_profileImage = profileImage;
	}

	public ProfileImageRemote getProfileImage() {
		return _profileImage;
	}

}
