package org.gwtcom.shared;

public class ProfileImageRemote extends BaseDomainRemote {

	private static final long serialVersionUID = 5597937747164985034L;

	private String _image;

	private String _imageThumb;

	public void setImage(String image) {
		_image = image;
	}

	public String getImage() {
		return _image;
	}

	public void setImageThumb(String imageThumb) {
		_imageThumb = imageThumb;
	}

	public String getImageThumb() {
		return _imageThumb;
	}
}
