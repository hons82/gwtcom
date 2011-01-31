package org.gwtcom.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class ProfileImage extends BaseDomainObject {

	private static final long serialVersionUID = 382224708168422399L;

	@Column(name = "userprofile")
	@OneToOne(mappedBy = "_profileImage")
	private UserProfile _userprofile;

	@Lob
	@Column(name = "picture")
	private byte[] _picture;

	@Transient
	private byte[] _pictureThumb;

	public ProfileImage() {
		_picture = new byte[0];
	}

	public void setPicture(byte[] picture) {
		_picture = picture;
	}

	public byte[] getPicture() {
		return _picture;
	}

	public void setPictureThumb(byte[] pictureThumb) {
		_pictureThumb = pictureThumb;
	}

	public byte[] getPictureThumb() {
		return _pictureThumb != null ? _pictureThumb : new byte[0];
	}

	public void setUserprofile(UserProfile userprofile) {
		_userprofile = userprofile;
	}

	public UserProfile getUserprofile() {
		return _userprofile;
	}
}