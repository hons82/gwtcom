package org.gwtcom.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.ShortBlob;

@Entity
public class ProfileImage extends BaseDomainObject {

	@Column(name = "userprofile")
	@OneToOne(mappedBy = "_profileImage")
	private UserProfile _userprofile;

	@Lob
	@Column(name = "picture")
	private Blob _picture;

	@Transient
	private ShortBlob _pictureThumb;

	public ProfileImage() {
		// _picture = new byte[0];
	}

	public void setPicture(final byte[] picture) {
		_picture = new Blob(picture);
	}

	public byte[] getPicture() {
		return _picture != null ? _picture.getBytes() : new byte[0];
	}

	public void setPictureThumb(byte[] pictureThumb) {
		// TODO: check if size smaller than 500 bytes
		_pictureThumb = new ShortBlob(pictureThumb);
	}

	public byte[] getPictureThumb() {
		return _pictureThumb != null ? _pictureThumb.getBytes() : new byte[0];
	}

	public void setUserprofile(UserProfile userprofile) {
		_userprofile = userprofile;
	}

	public UserProfile getUserprofile() {
		return _userprofile;
	}
}