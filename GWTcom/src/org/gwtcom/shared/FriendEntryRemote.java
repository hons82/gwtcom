package org.gwtcom.shared;

public class FriendEntryRemote extends ParentBaseDomainRemote {
	
	private String _name;

	private String _surname;

	private String _email;

	private int _gender;

	private ProfileImageRemote _profileImage;

	
	public FriendEntryRemote() {

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

	public void setProfileImage(ProfileImageRemote profileImage) {
		_profileImage = profileImage;
	}

	public ProfileImageRemote getProfileImage() {
		return _profileImage;
	}

}
